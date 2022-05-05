package boblovespihonkaimod.powers;

import basemod.interfaces.CloneablePowerInterface;
import boblovespihonkaimod.DefaultMod;
import boblovespihonkaimod.util.TextureLoader;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.HealthBarRenderPower;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class HonkaiPower extends AbstractPower implements CloneablePowerInterface, HealthBarRenderPower
{
	private static final Texture tex84 = TextureLoader.getTexture(DefaultMod.makePowerPath("placeholder_power84.png"));
	private static final Texture tex32 = TextureLoader.getTexture(DefaultMod.makePowerPath("placeholder_power32.png"));
	public static String id = DefaultMod.makeID("honkaiPower");
	public static final Color color = new Color(0x540049ff);
	public PowerStrings strings;
	private AbstractCreature source;

	public HonkaiPower(final AbstractCreature owner, final AbstractCreature source, final int amount)
	{
		ID = id;
		strings = CardCrawlGame.languagePack.getPowerStrings(ID);
		name = strings.NAME;

		this.owner = owner;
		this.source = source;
		this.amount = amount;

		type = PowerType.BUFF;
		isTurnBased = false;

		// We load those txtures here.
		this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
		this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

		priority = 0;

		updateDescription();
	}

	@Override
	public AbstractPower makeCopy()
	{
		return new HonkaiPower(owner, source, amount);
	}

	@Override
	public void updateDescription()
	{
		description = strings.DESCRIPTIONS[0];
	}

	@Override
	public int getHealthBarAmount()
	{
		return amount;
	}

	@Override
	public Color getColor()
	{
		return color;
	}
}
