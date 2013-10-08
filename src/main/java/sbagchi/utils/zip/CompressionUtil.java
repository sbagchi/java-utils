package sbagchi.utils.zip;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.GZIPInputStream;
import java.util.zip.Inflater;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** Compress / Decompress. */
public final class CompressionUtil
{
	  private static final Logger logger = LoggerFactory.getLogger(CompressionUtil.class); 
    /**
     * no instances.
     */
    private CompressionUtil()
    {
        // NO OP
    }

    /**
     * Decompress the byte array passed using a default buffer length of 1024.
     * <p>
     * @param input compressed byte array webservice response
     * @return uncompressed byte array
     */
    public static byte[] decompressByteArray( final byte[] input )
    {
        return decompressByteArray( input, 1024 );
    }

    /**
     * Decompress the byte array passed
     * <p>
     * @param input compressed byte array webservice response
     * @param bufferLength buffer length
     * @return uncompressed byte array
     */
    public static byte[] decompressByteArray( final byte[] input, final int bufferLength )
    {
        if ( null == input )
        {
            // TODO : log event error ?
            throw new IllegalArgumentException( "Input was null" );
        }

        // Create the decompressor and give it the data to compress
        final Inflater decompressor = new Inflater();

        decompressor.setInput( input );

        // Create an expandable byte array to hold the decompressed data
        final ByteArrayOutputStream baos = new ByteArrayOutputStream( input.length );

        // Decompress the data
        final byte[] buf = new byte[bufferLength];

        try
        {
            while ( !decompressor.finished() )
            {
                int count = decompressor.inflate( buf );
                baos.write( buf, 0, count );
            }
        }
        catch ( DataFormatException ex )
        {
            logger.error( "CompressionUtil.decompressByteArray", ex );
        }

        try
        {
            baos.close();
        }
        catch ( IOException ex )
        {
            logger.error( "CompressionUtil.decompressByteArray", ex );
        }

        return baos.toByteArray();
    }

    /**
     * Compress the byte array passed
     * <p>
     * @param input byte array
     * @return compressed byte array
     * @exception java.io.IOException thrown if we can't close the output stream
     */
    public static byte[] compressByteArray( byte[] input )
        throws IOException
    {
        return compressByteArray( input, 1024 );
    }

    /**
     * Compress the byte array passed
     * <p>
     * @param input byte array
     * @param bufferLength buffer length
     * @return compressed byte array
     * @exception java.io.IOException thrown if we can't close the output stream
     */
    public static byte[] compressByteArray( byte[] input, int bufferLength )
        throws IOException
    {
        // Compressor with highest level of compression
        Deflater compressor = new Deflater();
        compressor.setLevel( Deflater.BEST_COMPRESSION );

        // Give the compressor the data to compress
        compressor.setInput( input );
        compressor.finish();

        // Create an expandable byte array to hold the compressed data.
        // It is not necessary that the compressed data will be smaller than
        // the uncompressed data.
        ByteArrayOutputStream bos = new ByteArrayOutputStream( input.length );

        // Compress the data
        byte[] buf = new byte[bufferLength];
        while ( !compressor.finished() )
        {
            int count = compressor.deflate( buf );
            bos.write( buf, 0, count );
        }

        bos.close();

        // Get the compressed data
        return bos.toByteArray();

    }

    /**
     * decompress a gzip byte array, using a default buffer length of 1024
     * <p>
     * @param compressedByteArray gzip-compressed byte array
     * @return decompressed byte array
     * @throws java.io.IOException thrown if there was a failure to construct the GzipInputStream
     */
    public static byte[] decompressGzipByteArray( byte[] compressedByteArray )
        throws IOException
    {
        return decompressGzipByteArray( compressedByteArray, 1024 );
    }

    /**
     * decompress a gzip byte array, using a default buffer length of 1024
     * <p>
     * @param compressedByteArray gzip-compressed byte array
     * @param bufferlength size of the buffer in bytes
     * @return decompressed byte array
     * @throws java.io.IOException thrown if there was a failure to construct the GzipInputStream
     */
    public static byte[] decompressGzipByteArray( byte[] compressedByteArray, int bufferlength )
        throws IOException
    {
        ByteArrayOutputStream uncompressedStream = new ByteArrayOutputStream();

        GZIPInputStream compressedStream = new GZIPInputStream( new ByteArrayInputStream( compressedByteArray ) );

        byte[] buffer = new byte[bufferlength];

        int index = -1;

        while ( ( index = compressedStream.read( buffer ) ) != -1 )
        {
            uncompressedStream.write( buffer, 0, index );
        }

        return uncompressedStream.toByteArray();
    }
}