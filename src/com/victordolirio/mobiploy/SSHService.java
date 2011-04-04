package com.victordolirio.mobiploy;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public class SSHService {

	private static final String USER_NAME = "victor";
	private static final String SERVER_NAME = "172.16.64.129";
	private static final Integer PORT = 22;

	public void executeCommand() {
		try {

			execCommand();

		} catch (JSchException e) {
			throw new IllegalStateException(e);
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
	}

	private void execCommand() throws JSchException, IOException {

		JSch jSch = new JSch();
		Session sshSession = jSch.getSession(USER_NAME, SERVER_NAME, PORT);

		RemoteUserInfo remoteUserInfo = new RemoteUserInfo();
		sshSession.setUserInfo(remoteUserInfo);
		sshSession.connect();

		Channel sshChannel = sshSession.openChannel("exec");
		((ChannelExec) sshChannel).setCommand("touch file.txt");

		sshChannel.setInputStream(null);

		ByteArrayOutputStream sshStandardErrorOutputStream = new ByteArrayOutputStream();
		((ChannelExec) sshChannel).setErrStream(sshStandardErrorOutputStream);

		InputStream inputStream = sshChannel.getInputStream();

		sshChannel.connect();

		byte[] inputBuffer = new byte[1024];

		while (true) {

			while (inputStream.available() > 0) {
				int input = inputStream.read(inputBuffer, 0, 1024);
				if (input < 0)
					break;
			}

			if (sshChannel.isClosed()) {
				// TODO tratar fechamento do channel
				break;
			}
		}

		sshChannel.disconnect();
		sshSession.disconnect();
	}
}
