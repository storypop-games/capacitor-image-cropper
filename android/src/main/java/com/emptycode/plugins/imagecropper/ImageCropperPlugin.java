package com.emptycode.plugins.imagecropper;

import java.io.File;
import java.io.IOException;
import java.io.ByteArrayOutputStream;

import android.net.Uri;
import android.util.Base64;
import android.app.Activity;
import android.os.Environment;
import android.graphics.Color;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;

import com.getcapacitor.Plugin;
import com.getcapacitor.JSObject;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

import com.yalantis.ucrop.UCrop;

@CapacitorPlugin(name = "ImageCropper")
public class ImageCropperPlugin extends Plugin {
    private ActivityResultLauncher<Intent> cropActivityLauncher;

    /**
     * @return void
     * Loads the plugin.
     */
    @Override
    public void load() {
        super.load();
        cropActivityLauncher = getActivity().registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> handleCropResult(result)
        );
    }

    /**
     * @param call PluginCall
     * @return void
     * Crops an image.
     */
    @PluginMethod
    public void crop(PluginCall call) {
        try {
            String sourcePath = call.getString("source");
            if (sourcePath == null || sourcePath.isEmpty()) {
                call.reject("Source path is required");
                return;
            }

            saveCall(call); // Save the call for later resolution

            int quality = call.getInt("quality", 90);
            int aspectRatioX = call.getInt("aspectRatioX", 1);
            int aspectRatioY = call.getInt("aspectRatioY", 1);
            boolean circle = call.getBoolean("circle", false);
            String activeControlsWidgetColor = call.getString("activeControlsWidgetColor", "#9ef500");

            Uri sourceUri = Uri.parse(sourcePath);
            Uri destinationUri = createDestinationUri();

            UCrop.Options options = new UCrop.Options();
            options.setCompressionFormat(Bitmap.CompressFormat.JPEG);
            options.setCompressionQuality(quality);
            options.setCircleDimmedLayer(circle);
            options.setActiveControlsWidgetColor(Color.parseColor(activeControlsWidgetColor));

            Intent uCropIntent = UCrop.of(sourceUri, destinationUri)
                .withAspectRatio(aspectRatioX, aspectRatioY)
                .withOptions(options)
                .getIntent(getContext());

            cropActivityLauncher.launch(uCropIntent);
        } catch (Exception e) {
            call.reject("Failed to start crop: " + e.getMessage());
        }
    }

    /**
     * @param result ActivityResult
     * @return void
     * Handles the result of the crop activity.
     */
    private void handleCropResult(ActivityResult result) {
        PluginCall savedCall = getSavedCall();

        if (savedCall == null) {
            return; // No saved call to resolve
        }

        if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
            Uri resultUri = UCrop.getOutput(result.getData());
            if (resultUri != null) {
                try {
                    String croppedBase64 = convertImageToBase64(resultUri);

                    JSObject response = new JSObject();
                    response.put("path", resultUri.toString());
                    response.put("base64", croppedBase64);
                    savedCall.resolve(response);
                } catch (Exception e) {
                    savedCall.reject("Failed to process cropped image: " + e.getMessage());
                }
            } else {
                savedCall.reject("Cropping failed: No output URI");
            }
        } else if (result.getResultCode() == Activity.RESULT_CANCELED) {
            savedCall.reject("Cropping cancelled");
        } else {
            savedCall.reject("Cropping failed");
        }
    }

    /**
     * @return Uri
     * @throws ImageCropperException
     * Creates a Uri for the cropped image destination.
     */
    private Uri createDestinationUri() throws ImageCropperException {
        File destinationDir = new File(
            getContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES), 
            "ImageCropper"
        );
        if (!destinationDir.exists() && !destinationDir.mkdirs()) {
            throw new ImageCropperException("Failed to create directory: " + destinationDir.getAbsolutePath());
        }

        String destinationFileName = "CROPPED_" + System.currentTimeMillis() + ".jpg";
        File destinationFile = new File(destinationDir, destinationFileName);
        return Uri.fromFile(destinationFile);
    }

    /**
     * @param imageUri Uri
     * @return String
     * Converts an image to a Base64 string.
     */
    private String convertImageToBase64(Uri imageUri) {
        try {
            Bitmap bitmap = BitmapFactory.decodeStream(getContext().getContentResolver().openInputStream(imageUri));
            return encodeToBase64(bitmap, Bitmap.CompressFormat.JPEG, 100);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @param image Bitmap
     * @param compressFormat Bitmap.CompressFormat
     * @param quality int
     * @return String
     * Encodes the Bitmap into a Base64 string.
     */
    private String encodeToBase64(Bitmap image, Bitmap.CompressFormat compressFormat, int quality) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        image.compress(compressFormat, quality, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(byteArray, Base64.DEFAULT);
    }

    /**
     * @throws ImageCropperException
     * Custom exception for ImageCropperPlugin
     */
    public class ImageCropperException extends Exception {
        public ImageCropperException(String message) {
            super(message);
        }
    }
}
