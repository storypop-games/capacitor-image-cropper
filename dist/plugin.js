var capacitorImageCropper = (function (exports, core) {
    'use strict';

    const ImageCropper = core.registerPlugin('ImageCropper', {
        web: () => Promise.resolve().then(function () { return web; }).then((m) => new m.ImageCropperWeb()),
    });

    class ImageCropperWeb extends core.WebPlugin {
        async crop(options) {
            return {
                path: options.source,
            };
        }
    }

    var web = /*#__PURE__*/Object.freeze({
        __proto__: null,
        ImageCropperWeb: ImageCropperWeb
    });

    exports.ImageCropper = ImageCropper;

    return exports;

})({}, capacitorExports);
//# sourceMappingURL=plugin.js.map
