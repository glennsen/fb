package de.glenn.fizzbuzz;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import org.apache.commons.cli.*;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.UUID;
import java.util.regex.Pattern;

/**
 * Created by glenn on 25.04.16.
 *
 *
 */
public class Main {

    /**
     * default values
     */
    public static int START = 1;
    public static int END   = 100;

    /**
     * for parsing user input
     */
    public static Pattern INT_PATTERN     = Pattern.compile( "[\\-]?\\d+" );
    public static Pattern VERSION_PATTERN = Pattern.compile( "[1-3]" );


    /**
     *
     * @param args
     */
    public static void main( String... args ) {

        Options options = new Options();
        options.addOption(
                Option.builder()
                        .required( false )
                        .longOpt( "help" )
                        .desc( "chose version 1,2,3. For version 3 output will go directly to a newly created file working directory" )
                        .build()
        );

        CommandLineParser parser = new DefaultParser();

        try {

            CommandLine line = parser.parse( options, args );

            if( line.hasOption( "help" ) ) {

                HelpFormatter formatter = new HelpFormatter();
                formatter.printHelp( "Main", options, true );
                System.exit( 0 );

            }

        } catch( ParseException exp ) {

            System.err.println( "Parsing failed.  Reason: " + exp.getMessage() );
            System.exit( -1 );

        }


        // using defaults
        Integer start = START;
        Integer end   = END;

        Byte version = null;

        try {

            version = askUserForVersion();
            if ( version == null ) {

                System.err.println( "wrong version selected, exiting now." );
                System.exit( -1 );

            }


            switch ( version ) {

                case 1:

                    // printing out a fizzBuzzed-int-range to stdout
                    System.out.println( fizzBuzz( start, end ) );
                    break;

                case 2:

                    // ask user for int-range, start and int must be valid int values, <start> have to be smaller or equal than <end>
                    start = askUserForIntAndValidate( "start");
                    end   = askUserForIntAndValidate( "end" );

                    if ( start != null && end != null && start <= end ) {

                        System.out.println( fizzBuzz( start, end ) );

                    } else {

                        System.err.println( "<start> and <end> may not exceed 9 characters each.\n<end> must be bigger or equal than <start>, exiting now." );
                        System.exit( -1 );

                    }

                    break;

                case 3:

                    // ask user for int-range, start and int must be valid int values, <start> have to be smaller or equal than <end>
                    start = askUserForIntAndValidate( "start");
                    end   = askUserForIntAndValidate( "end" );

                    if ( start != null && end != null && start <= end ) {

                        String filename = UUID.randomUUID().toString() + ".fizzbuzz";
                        Files.write(
                                fizzBuzz( start, end ),
                                new File( filename ) ,
                                Charsets.UTF_8
                        );

                    } else {

                        System.err.println( "<start> and <end> may not exceed 9 characters each.\n<end> must be bigger or equal than <start>, exiting now." );

                    }

                    break;

                default:

                    System.err.println( "Something went wrong!" );
                    System.exit( -1 );
                    break;

            }

        } catch ( NumberFormatException nfe ) {

            System.err.println( "number parsing error: " + nfe.getMessage() );
            System.exit( -1 );

        } catch ( IOException e ) {

            System.err.println( "io error while writing to file: " + e.getMessage() );
            System.exit( -1 );
        }

    }


    /**
     * ask user for version, 1,2 or 3 is allowed,
     * user input must match VERSION_PATTERN
     *
     * @return version as Byte value from user input, can be Null
     * @throws NumberFormatException if user input cannot be parsed as byte value
     */
    protected static Byte askUserForVersion() throws NumberFormatException {

        Byte chosenVersion = null;

        Scanner reader = new Scanner( System.in );
        System.out.print( "chose version (1, 2 or 3): " );
        String s = reader.next();

        if ( s != null && VERSION_PATTERN.matcher( s ).matches() ) {

            chosenVersion = Byte.parseByte( s );

        }

        return chosenVersion;
    }


    /**
     * ask user for value, validate with following rules:
     *  value must match INT_PATTERN
     *  value may not exceed 9 characters, including "-" sign
     *  value must be parsable as Integer
     *
     * @param intType to provide user with information if input is <start> or <end> value.
     * @return Integer value parsed from user input and validated
     * @throws NumberFormatException if input value cannot be parsed as Integer
     */
    protected static Integer askUserForIntAndValidate( String intType ) throws NumberFormatException {

        Integer intValue = null;

        Scanner reader = new Scanner( System.in );
        System.out.print( "provide number from int range as <" + intType + "> value : " );
        String s = reader.next();

        if ( s != null && s.length() <= 9 && INT_PATTERN.matcher( s ).matches() ) {

            intValue = Integer.parseInt( s );

        }

        return intValue;
    }


    /**
     * builds a StringBuilder-Object holding a string with following rules:
     *  numbers from <start> to <end>
     *  if number is divisible by 3 and 5, take "FizzBuzz" instead
     *  if number is divisible by 3, take "Fizz" instead
     *  if number is divisible by 5, take "Buzz" instead
     *  all elements separated by <space>-sign
     *
     * @param start
     * @param end
     * @return fizzBuzzed StringBuilder-Object from start to end
     */
    protected static StringBuilder fizzBuzz( int start, int end ) {

        StringBuilder sb = new StringBuilder();

        for ( int i = start; i <= end; i++ ) {

            if ( i%3 == 0 && i%5 == 0) {
                sb.append( "FizzBuzz" );
            } else if ( i%3 == 0 ) {
                sb.append( "Fizz" );
            } else if ( i%5 == 0 ) {
                sb.append( "Buzz" );
            } else {
                sb.append( i );
            }

            sb.append( " " );

        }

        return sb;
    }

}
