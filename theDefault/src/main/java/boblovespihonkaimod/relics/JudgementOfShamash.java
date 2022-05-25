package boblovespihonkaimod.relics;

import basemod.abstracts.CustomRelic;
import boblovespihonkaimod.DefaultMod;
import boblovespihonkaimod.powers.BurningPower;
import boblovespihonkaimod.util.TextureLoader;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class JudgementOfShamash extends CustomRelic
{
	private static final Texture IMG = TextureLoader.getTexture(DefaultMod.makeRelicPath("placeholder_relic.png"));
	private static final Texture OUTLINE = TextureLoader.getTexture(DefaultMod.makeRelicOutlinePath("placeholder_relic.png"));
	public static String ID = DefaultMod.makeID("judgementOfShamash");

	public JudgementOfShamash()
	{
		super(ID, IMG, OUTLINE, RelicTier.UNCOMMON, LandingSound.SOLID);
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
		for (AbstractMonster monster : AbstractDungeon.getMonsters().monsters)
		{
			addToTop(new ApplyPowerAction(monster, monster, new BurningPower(monster, AbstractDungeon.player, 4)));
		}
	}
}
