import { WebPlugin } from '@capacitor/core';

import type { CropOptions, CropResult, ImageCropperPlugin } from './definitions';

export class ImageCropperWeb extends WebPlugin implements ImageCropperPlugin {
  async crop(options: CropOptions): Promise<CropResult> {
    console.log('ECHO', options);
    return {
      path: options.source,
    };
  }
}
