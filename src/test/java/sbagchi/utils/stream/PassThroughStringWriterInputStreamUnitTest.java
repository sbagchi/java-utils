package sbagchi.utils.stream;

import java.io.ByteArrayInputStream;

import junit.framework.TestCase;

public class PassThroughStringWriterInputStreamUnitTest
    extends TestCase
{
    /**
     * @throws Exception on error
     */
    public void testSimpleCount()
        throws Exception
    {
        ByteArrayInputStream bufferIs = new ByteArrayInputStream( "take this string".getBytes("UTF8") );
        PassThroughStringWriterInputStream is = new PassThroughStringWriterInputStream( bufferIs );
        int chr;
        while ( ( chr = is.read() ) != -1 ) {

        }
        is.read();
        assertEquals( "wrong string", "take this string", is.getStringWriter().toString() );
    }	
}