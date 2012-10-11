/**
 * Java Settlers - An online multiplayer version of the game Settlers of Catan
 * This file Copyright (C) 2011-2012 Jeremy D Monin <jeremy@nand.net>
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
package soc.message;

import java.util.StringTokenizer;

/**
 * This server-broadcast message announces a player
 * is moving a piece that's already on the board, to a new location.
 * This is a response to all player clients, following a player's {@link SOCMovePieceRequest}.
 *
 * @author Jeremy D Monin &lt;jeremy@nand.net&gt;
 * @since 2.0.00
 * @see SOCMoveRobber
 */
public class SOCMovePiece extends SOCMessageTemplate4i
{
    private static final long serialVersionUID = 2000L;

    /**
     * Create a SOCMovePiece message.
     *
     * @param ga  the name of the game
     * @param pn  the player number
     * @param ptype  piece type, such as {@link soc.game.SOCPlayingPiece#ROAD}
     * @param fromCoord  move piece from this coordinate
     * @param toCoord  move piece to this coordinate
     */
    public SOCMovePiece(String ga, final int pn, final int ptype, final int fromCoord, final int toCoord)
    {
        super(MOVEPIECE, ga, pn, ptype, fromCoord, toCoord);
    }

    /**
     * @return the player number
     */
    public int getPlayerNumber()
    {
        return p1;
    }

    /**
     * @return the piece type, such as {@link soc.game.SOCPlayingPiece#ROAD}
     */
    public int getPieceType()
    {
        return p2;
    }

    /**
     * @return the coordinate to move the piece from
     */
    public int getFromCoord()
    {
        return p3;
    }

    /**
     * @return the coordinate to move the piece to
     */
    public int getToCoord()
    {
        return p4;
    }

    /**
     * MOVEPIECE sep game sep2 playernumber sep2 ptype sep2 fromCoord sep2 toCoord
     *
     * @param ga  the name of the game
     * @param pn  the player number
     * @param ptype  piece type, such as {@link soc.game.SOCPlayingPiece#ROAD}
     * @param fromCoord  move piece from this coordinate
     * @param toCoord  move piece to this coordinate
     * @return the command string
     */
    public static String toCmd(String ga, int pn, int ptype, int fromCoord, int toCoord)
    {
        return MOVEPIECE + sep + ga + sep2 + pn + sep2
            + ptype + sep2 + fromCoord + sep2 + toCoord;
    }

    /**
     * Parse the command String into a SOCMovePiece message
     *
     * @param s   the String to parse: MOVEPIECE sep game sep2 playernumber sep2 ptype sep2 fromCoord sep2 toCoord
     * @return    a SOCMovePiece message, or null if the data is garbled
     */
    public static SOCMovePiece parseDataStr(String s)
    {
        String ga; // the game name
        int pn;    // the player number
        int pc;    // piece type
        int fc;    // 'from' coordinate
        int tc;    // 'to' coordinate

        StringTokenizer st = new StringTokenizer(s, sep2);

        try
        {
            ga = st.nextToken();
            pn = Integer.parseInt(st.nextToken());
            pc = Integer.parseInt(st.nextToken());
            fc = Integer.parseInt(st.nextToken());
            tc = Integer.parseInt(st.nextToken());
        }
        catch (Exception e)
        {
            return null;
        }

        return new SOCMovePiece(ga, pn, pc, fc, tc);
    }

    /**
     * Minimum version where this message type is used.
     * MOVEPIECE introduced in 2.0.00.
     * @return Version number, 2000 for JSettlers 2.0.00.
     */
    public int getMinimumVersion()
    {
        return 2000;
    }

}
