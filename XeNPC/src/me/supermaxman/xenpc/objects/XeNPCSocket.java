package me.supermaxman.xenpc.objects;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class XeNPCSocket extends Socket {

	@Override
	public InputStream getInputStream() {
		return new ByteArrayInputStream(new byte[10]);
	}
	
	@Override
	public OutputStream getOutputStream() {
		return new ByteArrayOutputStream();
	}
}