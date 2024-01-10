package shape;

/**
 * Custom Exception which have to be caught which should be thrown when a
 * TetrisShape tries to draw itself on the game field such that it covers a tile
 * of another shape.
 * 
 * @author Gabriel Glaser
 */
public class CannotOverdrawException extends Exception {
}
