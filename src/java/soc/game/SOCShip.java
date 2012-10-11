/**
 * Java Settlers - An online multiplayer version of the game Settlers of Catan
 * This file Copyright (C) 2011-2012 Jeremy D Monin <jeremy@nand.net>
 * Portions of this file Copyright (C) 2003 Robert S. Thomas
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 3
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * The maintainer of this program can be reached at jsettlers@nand.net
 **/

package soc.game;

/**
 * A ship playing piece, on the large sea board ({@link SOCBoardLarge}).
 * To simplify some game code, ships are a subclass of roads.
 * @author Jeremy D Monin <jeremy@nand.net>
 * @since 2.0.00
 */
public class SOCShip extends SOCRoad
{
    private static final long serialVersionUID = 2000L;

    /**
     * Is this ship part of an closed trade route, not an open one?
     */
    private boolean isClosed;

    /**
     * Make a new ship.
     * The ship is considered part of an open trade route, until {@link #setClosed()} is called. 
     *
     * @param pl  player who owns the ship
     * @param edge  edge coordinate
     * @param board  board if known; otherwise will extract from <tt>pl</tt>
     * @throws IllegalArgumentException  if <tt>pl</tt> null, or board null and <tt>pl.board</tt> also null
     */
    public SOCShip(SOCPlayer pl, final int edge, SOCBoard board)
        throws IllegalArgumentException
    {
        super(pl, edge, board);
        pieceType = SOCPlayingPiece.SHIP;
    }

    /**
     * Is this ship part of a closed trade route, not an open one?
     * @return  True if {@link #setClosed()} has been called
     */
    public boolean isClosed()
    {
        return isClosed;
    }

    /**
     * Call when this ship's trade route has been closed. 
     */
    public void setClosed()
    {
        isClosed = true;
    }

}
