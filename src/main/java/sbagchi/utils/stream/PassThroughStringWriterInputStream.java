package sbagchi.utils.stream;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

/** Captures the characters in the input string into a string writer. */
public class PassThroughStringWriterInputStream
    extends InputStream
{
    /** input stream */
    private InputStream inputStream;

    /** String writer */
    private StringWriter stringWriter;

    /**
     * TODO describe
     * @param inputStream input stream
     */
    public PassThroughStringWriterInputStream( InputStream inputStream )
    {
        setInputStream( inputStream );
        setStringWriter( new StringWriter() );
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
     * TODO describe
     * @param inputStream input stream
     * @param stringWriter string writer
     */
    public PassThroughStringWriterInputStream( InputStream inputStream, StringWriter stringWriter )
    {
        setInputStream( inputStream );
        setStringWriter( stringWriter );
    }

    /**
     * @return next char from the stream
     * @throws java.io.IOException on i/o error
     */
    public int read()
        throws IOException
    {
        int tempChar = getInputStream().read();
        if ( tempChar != -1 )
        {
            getStringWriter().write( tempChar );
        }
        return tempChar;
    }

    /**
     * @return Returns the inputStream.
     */
    public InputStream getInputStream()
    {
        return inputStream;
    }

    /**
     * @param inputStream The inputStream to set.
     */
    public void setInputStream( InputStream inputStream )
    {
        this.inputStream = inputStream;
    }

    /**
     * @return Returns the stringWriter.
     */
    public StringWriter getStringWriter()
    {
        return stringWriter;
    }

    /**
     * @param stringWriter The stringWriter to set.
     */
    public void setStringWriter( StringWriter stringWriter )
    {
        this.stringWriter = stringWriter;
    }
}
