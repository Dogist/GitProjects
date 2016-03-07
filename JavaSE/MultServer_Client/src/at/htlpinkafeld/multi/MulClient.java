/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.multi;

import java.net.*;
import java.io.*;
import java.util.Scanner;
/**
 *
 * @author Martin Six
 */


class MulClient
{
  public static void main( String[] args )
  {
    Socket server = null;

    try
    {
      server = new Socket( "localhost", 3141 );
      Scanner     in  = new Scanner( server.getInputStream() );
      PrintWriter out = new PrintWriter( server.getOutputStream(), true );

      out.println( "2" );
      out.println( "4" );
      System.out.println( in.nextLine() );

      server = new Socket( "localhost", 3141 );
      in  = new Scanner( server.getInputStream() );
      out = new PrintWriter( server.getOutputStream(), true );

      out.println( "23895737895" );
      out.println( "434589358935857" );
      System.out.println( in.nextLine() );
    }
    catch ( UnknownHostException e ) {
      e.printStackTrace();
    }
    catch ( IOException e ) {
      e.printStackTrace();
    }
    finally {
      if ( server != null )
        try { server.close(); } catch ( IOException e ) { }
    }
  }
}