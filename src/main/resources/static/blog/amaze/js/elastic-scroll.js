(function () {
    'use strict';

    if (window.matchMedia && window.matchMedia('(prefers-reduced-motion: reduce)').matches) {
        return;
    }

    var maxPull = 72;
    var touchDamping = 0.36;
    var wheelDamping = 0.18;
    var springStiffness = 0.12;
    var springDamping = 0.74;
    var startY = 0;
    var currentPull = 0;
    var springVelocity = 0;
    var pulling = false;
    var releaseTimer = null;
    var wheelTimer = null;
    var springFrame = null;

    function isEditable(target) {
        if (!target) {
            return false;
        }
        var tagName = target.tagName ? target.tagName.toLowerCase() : '';
        return tagName === 'input' || tagName === 'textarea' || tagName === 'select' || target.isContentEditable;
    }

    function isAtTop() {
        return window.scrollY <= 0 &&
            document.documentElement.scrollTop <= 0 &&
            document.body.scrollTop <= 0;
    }

    function setPull(value) {
        window.cancelAnimationFrame(springFrame);
        springFrame = null;
        springVelocity = 0;
        currentPull = Math.max(0, Math.min(maxPull, value));
        document.body.classList.add('elastic-scroll-active');
        document.body.style.setProperty('--elastic-pull-offset', currentPull + 'px');
    }

    function releasePull() {
        if (currentPull <= 0) {
            pulling = false;
            document.body.classList.remove('elastic-scroll-return');
            return;
        }

        window.clearTimeout(releaseTimer);
        window.cancelAnimationFrame(springFrame);
        document.body.classList.add('elastic-scroll-return');
        pulling = false;

        function settle() {
            springVelocity += (0 - currentPull) * springStiffness;
            springVelocity *= springDamping;
            currentPull += springVelocity;

            if (Math.abs(currentPull) < 0.35 && Math.abs(springVelocity) < 0.35) {
                currentPull = 0;
                springVelocity = 0;
                document.body.style.setProperty('--elastic-pull-offset', '0px');
                document.body.classList.remove('elastic-scroll-active');
                document.body.classList.remove('elastic-scroll-return');
                springFrame = null;
                return;
            }

            document.body.style.setProperty('--elastic-pull-offset', Math.max(0, currentPull) + 'px');
            springFrame = window.requestAnimationFrame(settle);
        }

        springFrame = window.requestAnimationFrame(settle);
    }

    function handleTouchStart(event) {
        if (event.touches.length !== 1 || isEditable(event.target)) {
            return;
        }
        startY = event.touches[0].clientY;
        pulling = false;
    }

    function handleTouchMove(event) {
        if (event.touches.length !== 1) {
            return;
        }

        var pullDistance = event.touches[0].clientY - startY;
        if (pullDistance <= 0) {
            releasePull();
            return;
        }

        if (!pulling && !isAtTop()) {
            return;
        }

        pulling = true;
        document.body.classList.remove('elastic-scroll-return');
        setPull(pullDistance * touchDamping);
        event.preventDefault();
    }

    function handleWheel(event) {
        if (event.deltaY >= 0 || !isAtTop() || isEditable(event.target)) {
            return;
        }

        document.body.classList.remove('elastic-scroll-return');
        setPull(currentPull + Math.min(18, Math.abs(event.deltaY) * wheelDamping));
        event.preventDefault();

        window.clearTimeout(wheelTimer);
        wheelTimer = window.setTimeout(releasePull, 90);
    }

    window.addEventListener('touchstart', handleTouchStart, {passive: true});
    window.addEventListener('touchmove', handleTouchMove, {passive: false});
    window.addEventListener('touchend', releasePull, {passive: true});
    window.addEventListener('touchcancel', releasePull, {passive: true});
    window.addEventListener('wheel', handleWheel, {passive: false});
})();
