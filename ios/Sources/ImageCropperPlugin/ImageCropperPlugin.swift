import Foundation
import Capacitor
import TOCropViewController

@objc(ImageCropperPlugin)
public class ImageCropperPlugin: CAPPlugin, TOCropViewControllerDelegate {
    
    private var call: CAPPluginCall?
    
    @objc func crop(_ call: CAPPluginCall) {
        self.call = call
        
        guard let sourcePath = call.getString("source"),
              let image = UIImage(contentsOfFile: sourcePath) else {
            call.reject("Invalid image path")
            return
        }

        let quality = call.getInt("quality") ?? 90
        let aspectRatioX = call.getInt("aspectRatioX") ?? 1
        let aspectRatioY = call.getInt("aspectRatioY") ?? 1
        let circle = call.getBool("circle") ?? false

        DispatchQueue.main.async {
            let cropViewController = TOCropViewController(croppingStyle: circle ? TOCropViewCroppingStyle.circular : TOCropViewCroppingStyle.default, image: image)
            cropViewController.delegate = self
            cropViewController.aspectRatioPreset = .presetCustom
            cropViewController.customAspectRatio = CGSize(width: aspectRatioX, height: aspectRatioY)
            cropViewController.aspectRatioLockEnabled = true
            cropViewController.resetAspectRatioEnabled = false
            
            if circle {
                cropViewController.cropView.cropBoxResizeEnabled = false
            }

            self.bridge?.viewController?.present(cropViewController, animated: true)
        }
    }

    public func cropViewController(_ cropViewController: TOCropViewController, didFinishCancelled cancelled: Bool, withCroppedImage croppedImage: UIImage?) {
        cropViewController.dismiss(animated: true) {
            if cancelled {
                self.call?.reject("User cancelled cropping")
                return
            }

            guard let image = croppedImage, let data = image.jpegData(compressionQuality: 0.9) else {
                self.call?.reject("Cropping failed")
                return
            }

            let fileName = "\(UUID().uuidString).jpg"
            let filePath = FileManager.default.temporaryDirectory.appendingPathComponent(fileName)
            try? data.write(to: filePath)

            self.call?.resolve(["path": filePath.path])
        }
    }
}
