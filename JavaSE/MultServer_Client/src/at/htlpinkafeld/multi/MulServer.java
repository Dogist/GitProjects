/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.multi;

import java.io.*;
import java.math.BigInteger;
import java.net.*;
import java.util.Scanner;
/**
 *
 * @author Martin Six
 */


public class MulServer
{
  private static void handleConnection( Socket client ) throws IOException
  {
    Scanner     in  = new Scanner( client.getInputStream() );
    PrintWriter out = new PrintWriter( client.getOutputStream(), true );

    String factor1 = in.nextLine();
    String factor2 = in.nextLine();

    out.println( new BigInteger(factor1).multiply( new BigInteger(factor2) ) );
  }

  public static void main( String[] args ) throws IOException
  {
    ServerSocket server = new ServerSocket( 3141 );

    while ( true )
    {
      Socket client = null;

      try
      {
        client = server.accept();
        handleConnection ( client );
      }
      catch ( IOException e ) {
        e.printStackTrace();
      }
      finally {
        if ( client != null )
          try { client.close(); } catch ( IOException e ) { }
      }
    }
  }
}