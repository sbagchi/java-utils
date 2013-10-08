package sbagchi.utils.stream;

import java.io.ByteArrayInputStream;

import junit.framework.TestCase;

/**
 * @author Sorbo Bagchi
 */
public class CountingInputStreamUnitTest
    extends TestCase
{

    /**
     * @throws Exception on error
     */
    public void testSimpleCount()
        throws Exception
    {
        ByteArrayInputStream bufferIs = new ByteArrayInputStream( "1234567890".getBytes() );

        CountingInputStream is = new CountingInputStream( bufferIs );

        StringBuffer buffer = new StringBuffer();

        int chr;
        while ( ( chr = is.read() ) != -1 )
        {
            buffer.append( (char) chr );
        }
        // make sure no extra hits are recorded
        is.read();

        System.out.println( "count: " + is.getCount() );
        assertEquals( "Should have counted 10", 10, is.getCount() );
    }
}
