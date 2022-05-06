package boblovespihonkaimod.relics;

import basemod.abstracts.CustomRelic;
import boblovespihonkaimod.DefaultMod;
import boblovespihonkaimod.powers.HonkaiPower;
import boblovespihonkaimod.util.TextureLoader;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class DecayedFragment extends CustomRelic
{
	private static final Texture IMG = TextureLoader.getTexture(DefaultMod.makeRelicPath("placeholder_relic.png"));
	private static final Texture OUTLINE = TextureLoader.getTexture(DefaultMod.makeRelicOutlinePath("placeholder_relic.png"));
	public static String ID = DefaultMod.makeID("decayedFragment");

	public DecayedFragment()
	{
		super(ID, IMG, OUTLINE, RelicTier.COMMON, LandingSound.SOLID);
	}

	@Override
	public String getUpdatedDescription()
	{
		return DESCRIPTIONS[0];
	}

	@Override
	public void atBattleStart()
	{
		flash();
		addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
		HonkaiPower p = new HonkaiPower(AbstractDungeon.player, AbstractDungeon.player, 5);
		addToTop(new ApplyPowerAction(p.owner, p.owner, p));
	}
}
