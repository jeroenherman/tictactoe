package be.leerstad.tictactoe.service.dto;

public enum GameMode {
	 SINGLE("Single Player"),  DUAL("Dual Player");
	   private String value;
		private GameMode(String value) {
			this.value = value;
		}
		public String toString() {
			return this.value;
		}
	}	