package com.sapo.drakkar.common.exception;

/**
 * Created by giampaolo.saporito on 20/12/2016.
 */
public class DrakkarException extends Exception
{
	DrakkarException() {}

	DrakkarException(String msg) { super(msg); }

	DrakkarException(String message, Throwable cause) {
		super(buildMsgWithCause(message,cause), cause);
	}

	DrakkarException(String message, Class<?>  owner ) {
		super( buildMsgWithLauncher(message, owner) );
	}

	DrakkarException(String message, Class<?>  owner, Throwable cause ) {
		super( buildMsgWithLauncher( buildMsgWithCause(message,cause) , owner), cause );
	}

	private static String buildMsgWithLauncher( String msg, Class<?>  owner )
	{
		if( msg == null )	{ msg = ""; }
		if( owner == null ) { return msg; }

		StringBuilder sb = new StringBuilder();
		sb.append( DrakkarException.class.getName() );
		sb.append( " throw by").append( owner.getName() ).append(" : ").append( msg );

		return sb.toString();
	}

	private static String buildMsgWithCause( String msg, Throwable cause )
	{
		if( msg == null )	{ msg = ""; }
		if( cause == null ) { return msg; }

		StringBuilder sb = new StringBuilder();
		sb.append( DrakkarException.class.getName() );
		sb.append(" ").append(msg).append(" - caused by ")
				.append( cause.getClass().getName() )
				.append(" - ").append( cause.getMessage() );
		return sb.toString();
	}
}
