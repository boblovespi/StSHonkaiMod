package boblovespihonkaimod.relics;

import basemod.abstracts.CustomRelic;
import boblovespihonkaimod.DefaultMod;
import boblovespihonkaimod.powers.HonkaiPower;
import boblovespihonkaimod.util.TextureLoader;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.relics.OnReceivePowerRelic;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class AmplifierMatrix extends CustomRelic implements OnReceivePowerRelic
{
	private static final Texture IMG = TextureLoader.getTexture(DefaultMod.makeRelicPath("placeholder_relic.png"));
	private static final Texture OUTLINE = TextureLoader.getTexture(DefaultMod.makeRelicOutlinePath("placeholder_relic.png"));
	public static String ID = DefaultMod.makeID("amplifierMatrix");

	public AmplifierMatrix()
	{
		super(ID, IMG, OUTLINE, RelicTier.SHOP, LandingSound.SOLID);
	}

	@Override
	public String getUpdatedDescription()
	{
		return DESCRIPTIONS[0];
	}

	@Override
	public boolean onReceivePower(AbstractPower abstractPower, AbstractCreature abstractCreature)
	{
		return true;
	}

	@Override
	public int onReceivePowerStacks(AbstractPower power, AbstractCreature source, int stackAmount)
	{
		if (power instanceof HonkaiPower)
			return stackAmount + 1;
		return stackAmount;
	}
}
