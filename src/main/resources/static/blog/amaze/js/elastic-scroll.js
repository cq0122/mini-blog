(function () {
    'use strict';

    if (window.matchMedia && window.matchMedia('(prefers-reduced-motion: reduce)').matches) {
        return;
    }

    var maxPull = 28;
    var touchResistance = 0.16;
    var wheelResistance = 0.075;
    var wheelStepLimit = 5;
    var releaseDuration = 180;
    var startY = 0;
    var currentPull = 0;
    var pulling = false;
    var wheelTimer = null;
    var releaseFrame = null;

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
        window.cancelAnimationFrame(releaseFrame);
        releaseFrame = null;
        currentPull = Math.max(0, Math.min(maxPull, value));
        document.body.classList.add('elastic-scroll-active');
        document.body.style.setProperty('--elastic-pull-offset', currentPull + 'px');
    }

    function dampen(distance, resistance) {
        return Math.log1p(distance) * Math.max(0, distance) * resistance;
    }

    function easeOutCubic(progress) {
        return 1 - Math.pow(1 - progress, 3);
    }

    function releasePull() {
        if (currentPull <= 0) {
            pulling = false;
            document.body.classList.remove('elastic-scroll-return');
            return;
        }

        window.cancelAnimationFrame(releaseFrame);
        document.body.classList.add('elastic-scroll-return');
        pulling = false;

        var releaseStartPull = currentPull;
        var releaseStartTime = window.performance && window.performance.now ? window.performance.now() : Date.now();

        function settle(now) {
            var elapsed = now - releaseStartTime;
            var progress = Math.min(1, elapsed / releaseDuration);
            var easedProgress = easeOutCubic(progress);
            currentPull = releaseStartPull * (1 - easedProgress);

            if (progress >= 1 || currentPull <= 0.2) {
                currentPull = 0;
                document.body.style.setProperty('--elastic-pull-offset', '0px');
                document.body.classList.remove('elastic-scroll-active');
                document.body.classList.remove('elastic-scroll-return');
                releaseFrame = null;
                return;
            }

            document.body.style.setProperty('--elastic-pull-offset', currentPull + 'px');
            releaseFrame = window.requestAnimationFrame(settle);
        }

        releaseFrame = window.requestAnimationFrame(settle);
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
        setPull(dampen(pullDistance, touchResistance));
        event.preventDefault();
    }

    function handleWheel(event) {
        if (event.deltaY >= 0 || !isAtTop() || isEditable(event.target)) {
            return;
        }

        document.body.classList.remove('elastic-scroll-return');
        setPull(currentPull + Math.min(wheelStepLimit, dampen(Math.abs(event.deltaY), wheelResistance)));
        event.preventDefault();

        window.clearTimeout(wheelTimer);
        wheelTimer = window.setTimeout(releasePull, 72);
    }

    window.addEventListener('touchstart', handleTouchStart, {passive: true});
    window.addEventListener('touchmove', handleTouchMove, {passive: false});
    window.addEventListener('touchend', releasePull, {passive: true});
    window.addEventListener('touchcancel', releasePull, {passive: true});
    window.addEventListener('wheel', handleWheel, {passive: false});
})();
