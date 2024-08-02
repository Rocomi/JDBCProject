package com.kh.timer.run;

import javax.swing.SwingUtilities;

import com.kh.timer.BreakTime;

public class Run {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			new BreakTime().timer();
		});
	}

}
