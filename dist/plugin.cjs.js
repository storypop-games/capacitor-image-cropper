'use strict';

var core = require('@capacitor/core');

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
//# sourceMappingURL=plugin.cjs.js.map
