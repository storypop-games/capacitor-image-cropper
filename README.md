## 🌟 Available Now on Android, with iOS Support Coming Soon! 🌟

# 🎨 Capacitor Image Cropper

The **Capacitor Image Cropper** plugin offers a powerful and user-friendly way to crop images in your mobile applications. With this plugin, you can effortlessly select and crop images to meet your desired dimensions, ensuring your app looks polished and professional! ✨

## 🚀 Features

- **Flexible Cropping Options**: Crop images with customizable width, height, and aspect ratios. 📏
- **Quality Control**: Adjust the quality of the resulting image, balancing fidelity and file size. 📸
- **Circle Cropping**: Optionally crop images in a circular shape for a unique visual flair. 🔵
- **Cross-Platform Support**: Works seamlessly on both iOS and Android platforms. 📱
- **uCrop Integration**: For Android, we utilize the powerful **uCrop** library, providing a robust and feature-rich cropping experience. 🎉

## 📚 Usage

To get started with the Capacitor Image Cropper plugin, simply install it in your project and follow the provided documentation for implementation details.

Transform your images effortlessly with the **Capacitor Image Cropper** plugin! 🌈

### 🛠️ Adding JitPack Repository

To add the JitPack repository, navigate to your `android/gradle.build` file and include the following configuration:

```bash
allprojects {
    repositories {
        google()
        mavenCentral()
        maven { url "https://jitpack.io" }
    }
}
```

## 📦 Install

Run the following commands to install the plugin:

```bash
npm install capacitor-image-cropper
npx cap sync
```

## 📖 API

<docgen-index>

* [`crop(...)`](#crop)
* [Interfaces](#interfaces)

</docgen-index>

<docgen-api>
<!--Update the source file JSDoc comments and rerun docgen to update the docs below-->

### crop(...)

```typescript
crop(options: CropOptions) => Promise<CropResult>
```

| Param         | Type                                                |
| ------------- | --------------------------------------------------- |
| **`options`** | <code><a href="#cropoptions">CropOptions</a></code> |

**Returns:** <code>Promise&lt;<a href="#cropresult">CropResult</a>&gt;</code>

--------------------


### Interfaces


#### CropResult

| Prop         | Type                | Description                                                                             |
| ------------ | ------------------- | --------------------------------------------------------------------------------------- |
| **`path`**   | <code>string</code> | The path to the cropped image On Android: content:// or file:// URI On iOS: file:// URI |
| **`base64`** | <code>string</code> | Base64 encoded image data (optional, web only)                                          |


#### CropOptions

| Prop                            | Type                 | Description                                                                                   | Default                |
| ------------------------------- | -------------------- | --------------------------------------------------------------------------------------------- | ---------------------- |
| **`source`**                    | <code>string</code>  | The path or url of the image to crop Can be a local file path, content:// uri, or file:// uri |                        |
| **`quality`**                   | <code>number</code>  | Quality of the resulting image, between 0-100                                                 | <code>90</code>        |
| **`circle`**                    | <code>boolean</code> | Whether to crop the image in a circle                                                         | <code>false</code>     |
| **`width`**                     | <code>number</code>  | Maximum width of the resulting image                                                          | <code>300</code>       |
| **`height`**                    | <code>number</code>  | Maximum height of the resulting image                                                         | <code>300</code>       |
| **`aspectRatioX`**              | <code>number</code>  | Aspect ratio X for crop box (width/height) e.g., 1 for square                                 | <code>1</code>         |
| **`aspectRatioY`**              | <code>number</code>  | Aspect ratio Y for crop box (height/width) e.g., 1 for square                                 | <code>1</code>         |
| **`activeControlsWidgetColor`** | <code>string</code>  | Color of the active controls widget                                                           | <code>"#9ef500"</code> |

</docgen-api>

---

## 👤 Author

[**Rahul Kumar Sharma**](https://github.com/jerry4rahul)

Passionate developer and creator of the Capacitor Image Cropper plugin.

## 💬 Message

Thank you for using the Capacitor Image Cropper! Your feedback and contributions are always welcome. Let's make image cropping a breeze for everyone! 🌟

## 🤝 Collaboration

We welcome collaboration! If you have ideas, suggestions, or would like to contribute to the project, feel free to reach out or submit a pull request. Together, we can enhance this plugin and make it even better! 🚀

---

### 🎉 Special Thanks

A big shoutout to the [**uCrop**](https://github.com/Yalantis/uCrop) library for providing an excellent cropping experience on Android! Your contributions make this plugin even more powerful! 🙌
