package com.memorygame;

import java.awt.Color;

import java.awt.Font;

import java.util.List;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import java.util.ArrayList;

import java.util.Collections;

import javax.swing.JButton;

import javax.swing.JFrame;

import javax.swing.JLabel;

import javax.swing.SwingUtilities;

public class MemoryGame {

	private static int counter = 1;

	private static int score = 0;

	private static List<Integer> numberList = new ArrayList<>();

	private static int[] numberSequence = new int[10];

	private static List<JButton> buttonList = new ArrayList<>();

	private static Font font = new Font(Font.SANS_SERIF, Font.BOLD, 20);

	private static Font score_message = new Font(Font.SANS_SERIF, Font.BOLD, 15);

	private static JButton button1 = new JButton();

	private static JButton button2 = new JButton();

	private static JButton button3 = new JButton();

	private static JButton button4 = new JButton();

	private static JButton button5 = new JButton();

	private static JButton button6 = new JButton();

	private static JButton button7 = new JButton();

	private static JButton button8 = new JButton();

	private static JButton button9 = new JButton();

	private static JButton button10 = new JButton();

	private static JButton reset = new JButton();

	private static JButton play = new JButton();

	private static JLabel scoreLabel = new JLabel();

	private static JFrame frame = new JFrame("Memory Game");

	static {

		numberList.add(1);

		numberList.add(2);

		numberList.add(3);

		numberList.add(4);

		numberList.add(5);

		numberList.add(6);

		numberList.add(7);

		numberList.add(8);

		numberList.add(9);

		numberList.add(10);

		// set button names

		button1.setName("button1");

		button2.setName("button2");

		button3.setName("button3");

		button4.setName("button4");

		button5.setName("button5");

		button6.setName("button6");

		button7.setName("button7");

		button8.setName("button8");

		button9.setName("button9");

		button10.setName("button10");

		buttonList.add(button1);

		buttonList.add(button2);

		buttonList.add(button3);

		buttonList.add(button4);

		buttonList.add(button5);

		buttonList.add(button6);

		buttonList.add(button7);

		buttonList.add(button8);

		buttonList.add(button9);

		buttonList.add(button10);

	}

	private static void buildWindow() {

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setLayout(null);

		frame.setVisible(true);

		// set size and location of frame

		frame.setLocation(100, 150);

		frame.setSize(640, 500);

		// set background color

		frame.getContentPane().setBackground(Color.CYAN);

		setDefaultButtonsOnWindow();

	}

	private static void setDefaultButtonsOnWindow() {

		styleButtons();

		reset();

		addButtonsToPane();

		play.setEnabled(true);

	}

	private static void addButtonsToPane() {

		for (JButton button : buttonList) {

			frame.getContentPane().add(button);

		}

		frame.getContentPane().add(reset);

		frame.getContentPane().add(play);

		frame.getContentPane().add(scoreLabel);

	}

	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {

			public void run() {

				buildWindow();

			}

		});

	}

	private static void saveSequence(List<Integer> numberList) {

		for (int i = 0; i < 10; i++) {

			numberSequence[numberList.get(i) - 1] = i + 1;

		}

	}

	private static void reset() {

		Collections.shuffle(numberList);

		counter = 1;

		saveSequence(numberList);

		int i = 0;

		for (JButton button : buttonList) {

			button.setText(numberList.get(i).toString());

			i++;

		}

		play.setEnabled(true);

		disableButtons();

		scoreLabel.setText(null);

	}

	private static void play() {

		for (JButton button : buttonList) {

			button.setText("");

		}

		play.setEnabled(false);

		enableButtons();

		scoreLabel.setText(null);

		score = 0;

	}

	private static void styleButtons() {

		for (JButton button : buttonList) {

			button.setForeground(Color.LIGHT_GRAY);

			button.setFocusPainted(false);

			button.setBackground(Color.WHITE);

			button.setFont(font);

			button.addActionListener(new CheckClickedButton());

		}

		button1.setBounds(50, 50, 160, 30);

		button2.setBounds(230, 50, 160, 30);

		button3.setBounds(410, 50, 160, 30);

		button4.setBounds(50, 100, 160, 30);

		button5.setBounds(230, 100, 160, 30);

		button6.setBounds(410, 100, 160, 30);

		button7.setBounds(50, 150, 160, 30);

		button8.setBounds(230, 150, 160, 30);

		button9.setBounds(410, 150, 160, 30);

		button10.setBounds(230, 200, 160, 30);

		reset.setBounds(50, 300, 160, 30);

		play.setBounds(410, 300, 160, 30);

		reset.setForeground(Color.YELLOW);

		reset.setFocusPainted(false);

		reset.setBackground(Color.BLUE);

		reset.setText("RESET");

		reset.setFont(font);

		reset.addActionListener(new Reset());

		play.setForeground(Color.YELLOW);

		play.setFocusPainted(false);

		play.setBackground(Color.BLUE);

		play.setText("PLAY");

		play.setFont(font);

		play.addActionListener(new Play());

		scoreLabel.setForeground(Color.BLUE);

		scoreLabel.setFont(score_message);

		scoreLabel.setBounds(50, 350, 300, 60);

	}

	private static void enableButtons() {

		for (JButton button : buttonList) {

			button.setEnabled(true);

		}

	}

	private static void checkClickedButton(JButton button) {

		button.setEnabled(false);

		if (button.getName().equalsIgnoreCase("button" + numberSequence[counter - 1])) {

			button.setText("CORRECT");

			score++;

		} else

			button.setText("INCORRECT");

		if (counter == 10) {

			scoreLabel.setText("YOUR SCORE IS : " + score);

			return;

		}

		counter++;

	}

	private static void disableButtons() {

		for (JButton button : buttonList) {

			button.setEnabled(false);

		}

	}

	private static class Reset implements ActionListener {

		@Override

		public void actionPerformed(ActionEvent e) {

			MemoryGame.reset();

		}

	}

	private static class Play implements ActionListener {

		@Override

		public void actionPerformed(ActionEvent e) {

			MemoryGame.play();

		}

	}

	private static class CheckClickedButton implements ActionListener {

		@Override

		public void actionPerformed(ActionEvent e) {

			MemoryGame.checkClickedButton((JButton) e.getSource());

		}

	}

}