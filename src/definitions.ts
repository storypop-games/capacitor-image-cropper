export interface ImageCropperPlugin {
  crop(options: CropOptions): Promise<CropResult>;
}

export interface CropOptions {
  /**
   * The path or url of the image to crop
   * Can be a local file path, content:// uri, or file:// uri
   */
  source: string;

  /**
   * Quality of the resulting image, between 0-100
   * @default 90
   */
  quality?: number;

  /**
   * Whether to crop the image in a circle
   * @default false
   */
  circle?: boolean;

  /**
   * Maximum width of the resulting image
   * @default 300
   */
  width?: number;

  /**
   * Maximum height of the resulting image
   * @default 300
   */
  height?: number;

  /**
   * Aspect ratio X for crop box (width/height)
   * e.g., 1 for square
   * @default 1
   */
  aspectRatioX?: number;

  /**
   * Aspect ratio Y for crop box (height/width)
   * e.g., 1 for square
   * @default 1
   */
  aspectRatioY?: number;

  /**
   * Color of the active controls widget
   * @default "#9ef500"
   */
  activeControlsWidgetColor?: string;
}

export interface CropResult {
  /**
   * The path to the cropped image
   * On Android: content:// or file:// URI
   * On iOS: file:// URI
   */
  path: string;
  /**
   * Base64 encoded image data (optional, web only)
   */
  base64?: string;
}
