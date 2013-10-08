package sbagchi.utils.stream;

import java.io.IOException;
import java.io.InputStream;

/**
 * Counts the bytes passed through.
 * <p>
 * @author Sorbo Bagchi
 */
public class CountingInputStream
    extends InputStream
{
    /** The stream to count on. */
    private InputStream inputStream;

    /** number of bytes read */
    private int count = 0;

    /**
     * Uses the passed inputStream input stream to do the real work.
     * <p>
     * @param in -- an InputStream
     */
    public CountingInputStream( InputStream in )
    {
        this.setInputStream( in );
    }

    /**
     * @throws java.io.IOException on i/o error
     */
    public void close()
        throws IOException
    {
        getInputStream().close();
        super.close();
    }

    /**
     * Read a character from the stream and increment the count.
     * <p>
     * @return the read character
     * @throws java.io.IOException on i/o error
     */
    public int read()
        throws IOException
    {
        int tempChar = getInputStream().read();
        if ( tempChar != -1 )
        {
            count++;
        }
        return tempChar;
    }

    /**
     * The number of bytes that have passed through this stream.
     * <p>
     * @return count
     */
    public int getCount()
    {
        return this.count;
    }

    /**
     * @param inputStream The inputStream to set.
     */
    public void setInputStream( InputStream inputStream )
    {
        this.inputStream = inputStream;
    }

    /**
     * @return Returns the inputStream.
     */
    public InputStream getInputStream()
    {
        return inputStream;
    }

}