package com.victordolirio.mobiploy;

import com.jcraft.jsch.UserInfo;

public class RemoteUserInfo implements UserInfo {

	@Override
	public String getPassphrase() {
		return null;
	}

	@Override
	public String getPassword() {
		return "htlm1962";
	}

	@Override
	public boolean promptPassword(String message) {
		return false;
	}

	@Override
	public boolean promptPassphrase(String message) {
		return false;
	}

	@Override
	public boolean promptYesNo(String message) {
		return false;
	}

	@Override
	public void showMessage(String message) {
		System.out.println(message);
	}
}
