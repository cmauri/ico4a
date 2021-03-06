package divstar.ico4a.codec.ico;

import java.io.IOException;
import java.util.Locale;

import divstar.ico4a.io.LittleEndianInputStream;

/**
 * Represents an <tt>IconEntry</tt> structure, which contains information about an ICO image.
 *
 * @author Ian McDonagh
 * @author Igor Tykhyy
 */
public class IconEntry {
    /**
     * The width of the icon image in pixels.
     * <tt>0</tt> specifies a width of 256 pixels.
     */
    public int bWidth;
    /**
     * The height of the icon image in pixels.
     * <tt>0</tt> specifies a height of 256 pixels.
     */
    public int bHeight;
    /**
     * The number of colours, calculated from {@link #sBitCount sBitCount}.
     * <tt>0</tt> specifies a colour count of &gt;= 256.
     */
    public int bColorCount;
    /**
     * Unused.  Should always be <tt>0</tt>.
     */
    public byte bReserved;
    /**
     * Number of planes, which should always be <tt>1</tt>.
     */
    public short sPlanes;
    /**
     * Colour depth in bits per pixel.
     */
    public short sBitCount;
    /**
     * Size of ICO data, which should be the size of (InfoHeader + AND bitmap + XOR bitmap).
     */
    public int iSizeInBytes;
    /**
     * Position in file where the InfoHeader starts.
     */
    public int iFileOffset;

    public IconEntry(LittleEndianInputStream in) throws IOException {
        //Width 	1 byte 	Cursor Width (16, 32, 64, 0 = 256)
        bWidth = in.readUnsignedByte();
        //Height 	1 byte 	Cursor Height (16, 32, 64, 0 = 256 , most commonly = Width)
        bHeight = in.readUnsignedByte();
        //ColorCount 	1 byte 	Number of Colors (2,16, 0=256)
        bColorCount = in.readUnsignedByte();
        //Reserved 	1 byte 	=0
        bReserved = in.readByte();
        //Planes 	2 byte 	=1
        sPlanes = in.readShortLE();
        //BitCount 	2 byte 	bits per pixel (1, 4, 8)
        sBitCount = in.readShortLE();
        //SizeInBytes 	4 byte 	Size of (InfoHeader + ANDbitmap + XORbitmap)
        iSizeInBytes = in.readIntLE();
        //FileOffset 	4 byte 	FilePos, where InfoHeader starts
        iFileOffset = in.readIntLE();
    }

    /**
     * A string representation of this <tt>IconEntry</tt> structure.
     */
    public String toString() {
        return String.format(
                Locale.US,
                "width=%d,height=%d,bitCount=%s,colorCount=%d,reserved=%s,offset=%d,iSizeInBytes=%d,splanes=%s",
                bWidth,
                bHeight,
                sBitCount,
                bColorCount,
                bReserved,
                iFileOffset,
                iSizeInBytes,
                sPlanes
        );
    }
}
