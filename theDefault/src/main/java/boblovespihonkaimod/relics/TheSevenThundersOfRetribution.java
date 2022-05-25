package boblovespihonkaimod.relics;

import basemod.abstracts.CustomRelic;
import boblovespihonkaimod.DefaultMod;
import boblovespihonkaimod.cards.attacks.Railgun;
import boblovespihonkaimod.util.TextureLoader;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.relics.ClickableRelic;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

public class TheSevenThundersOfRetribution extends CustomRelic implements ClickableRelic
{
	// ID, images, text.
	public static final String ID = DefaultMod.makeID("theSevenThundersOfRetribution");

	private static final Texture IMG = TextureLoader.getTexture(
			DefaultMod.makeRelicPath("default_clickable_relic.png"));
	private static final Texture OUTLINE = TextureLoader.getTexture(
			DefaultMod.makeRelicOutlinePath("default_clickable_relic.png"));

	private boolean usedThisCombat = false; // You can also have a relic be only usable once per combat. Check out Hubris for more examples, including other StSlib things.
	private boolean isPlayerTurn = false; // We should make sure the relic is only activateable during our turn, not the enemies'.
    private int timesLeft = 7;

	public TheSevenThundersOfRetribution()
	{
		super(ID, IMG, OUTLINE, RelicTier.RARE, LandingSound.CLINK);
		setCounter(timesLeft);
	}


	@Override
	public void onRightClick()
	{
		if (!isObtained || usedThisCombat || !isPlayerTurn || timesLeft <= 0)
		{
			return;
		}
		if (AbstractDungeon.getCurrRoom() != null && AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT)
		{
			usedThisCombat = true;
            timesLeft--;
			flash();
			stopPulse();
			AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(new Railgun()));
            grayscale = true;
			if (timesLeft >= 0)
				setCounter(timesLeft);
			else
			{
				this.description = DESCRIPTIONS[1];
				setCounter(-1);
			}
		}
	}

	// Description
	@Override
	public String getUpdatedDescription()
	{
		return DESCRIPTIONS[0];
	}

	@Override
	public void atPreBattle()
	{
        if (timesLeft > 0)
        {
            grayscale = false;
            usedThisCombat = false; // Make sure usedThisTurn is set to false at the start of each combat.
            beginLongPulse();     // Pulse while the player can click on it.
        }
	}

	public void atTurnStart()
	{
        if (timesLeft > 0)
        {
            isPlayerTurn = true; // It's our turn!
            beginLongPulse(); // Pulse while the player can click on it.
        }
	}

	@Override
	public void onPlayerEndTurn()
	{
		isPlayerTurn = false; // Not our turn now.
		stopPulse();
	}

	@Override
	public void onVictory()
	{
		stopPulse(); // Don't keep pulsing past the victory screen/outside of combat.
        if (timesLeft >= 0)
            grayscale = false;
	}
}
