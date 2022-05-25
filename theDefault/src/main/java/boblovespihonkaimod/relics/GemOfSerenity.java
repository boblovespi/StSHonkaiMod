package boblovespihonkaimod.relics;

import basemod.abstracts.CustomRelic;
import boblovespihonkaimod.DefaultMod;
import boblovespihonkaimod.util.TextureLoader;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class GemOfSerenity extends CustomRelic
{
	private static final Texture IMG = TextureLoader.getTexture(DefaultMod.makeRelicPath("placeholder_relic.png"));
	private static final Texture OUTLINE = TextureLoader.getTexture(
			DefaultMod.makeRelicOutlinePath("placeholder_relic.png"));
	public static String ID = DefaultMod.makeID("gemOfSerenity");

	public GemOfSerenity()
	{
		super(ID, IMG, OUTLINE, RelicTier.BOSS, LandingSound.SOLID);
	}

	@Override
	public String getUpdatedDescription()
	{
		return DESCRIPTIONS[0];
	}

	public void onEquip()
	{
		AbstractDungeon.player.energy.energyMaster += 1;
	}

	public void onUnequip()
	{
		AbstractDungeon.player.energy.energyMaster -= 1;
	}

	@Override
	public void onVictory()
	{
		AbstractDungeon.player.decreaseMaxHealth(1);
	}
}
