# ico4a
This is a library to decode ICO files into a list of Bitmap-objects - based on image4j (along with a demo app showing off the library functionality).

## Usage
In order to use the ICO4A library, you have to include it in your project's dependencies (currently only by downloading the AAR file and including it as a library).

After having done so, include the following statement anywhere.
	List<Bitmap> images = ICODecoder.read(SOME_INPUTSTREAM);

I suggest creating an AsyncTask to download and / or read the file, because while reading the file is usually a simple task, it may take some time to do so and you would not want to block the UI thread for that long.

See the sample app included in the gitHub repository to get an idea of how to use it.

## Limitations
ICO4A cannot write ICO files, because I did not need it myself. Some routines are already supplied. The best way to create the encoders is to have a look at the [image4j project](https://github.com/imcdonagh/image4j "image4j project") and implement the missing encoder classes.

## Stability
ICO4A has been tested with various single- and multi-image ICO files. It has been tested to support 1-, 4-, 8-, 24- and 32-bit uncompressed images with and without alpha transparency. In addition 24- and 32-bit PNG-compressed images with and without alpha transparency have been tested. All of them loaded without any problems.

## Final word
Please report any problems if you encounter them and I will see if I can help solve them.