package org.adventure.commands;

import org.adventure.GameState;

public abstract class CommandCondition {

		public abstract boolean conditional(GameState state);
}
