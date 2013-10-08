package sbagchi.utils.zip;

import java.io.ByteArrayOutputStream;
import java.util.zip.GZIPOutputStream;

import junit.framework.TestCase;

public class CompressionUtilUnitTest
    extends TestCase
{
    /** Test method for decompressByteArray. */
    public final void testDecompressByteArray_failure()
    {
        try
        {
            CompressionUtil.decompressByteArray( null );
            fail( "excepted an IllegalArgumentException" );
        }
        catch ( IllegalArgumentException exception )
        {
            return;
        }
    }

    /** Test method for decompressByteArray. */
    public final void testCompressDecompressByteArray_success()
    {
        String text = "some text";
        try
        {
            byte[] compressedText = CompressionUtil.compressByteArray( text.getBytes() );

            byte[] output = CompressionUtil.decompressByteArray( compressedText );

            String result = new String( output );

            assertNotNull( " decompressed output stream shouldn't have been null ", output );

            assertEquals( text, result );
        }
        catch ( Exception exception )
        {
            fail( " We shouldn't have an exception " + exception.getMessage() );
        }
    }

    /** Test method for decompressByteArray. */
    public final void testCompressDecompressGzipByteArray_success()
    {
        String text = " something else here";
        try
        {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            GZIPOutputStream os = new GZIPOutputStream( baos );

            os.write( text.getBytes() );
            os.flush();
            os.close();

            byte[] output = CompressionUtil.decompressGzipByteArray( baos.toByteArray() );

            String result = new String( output );

            assertNotNull( " decompressed output stream shouldn't have been null ", output );

            assertEquals( text, result );
        }
        catch ( Exception exception )
        {
            fail( " We shouldn't have an exception ::" + exception.getMessage() );
        }
    }
}
