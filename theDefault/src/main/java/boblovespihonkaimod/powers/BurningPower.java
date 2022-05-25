package boblovespihonkaimod.powers;

import basemod.interfaces.CloneablePowerInterface;
import boblovespihonkaimod.DefaultMod;
import boblovespihonkaimod.util.TextureLoader;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.HealthBarRenderPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class BurningPower extends AbstractPower implements CloneablePowerInterface, HealthBarRenderPower
{
	public static final Color color = new Color(0xFF9922ff);
	private static final Texture tex84 = TextureLoader.getTexture(DefaultMod.makePowerPath("placeholder_power84.png"));
	private static final Texture tex32 = TextureLoader.getTexture(DefaultMod.makePowerPath("placeholder_power32.png"));
	public static String id = DefaultMod.makeID("burningPower");
	public PowerStrings strings;
	private AbstractCreature source;

	public BurningPower(final AbstractCreature owner, final AbstractCreature source, final int amount)
	{
		ID = id;
		strings = CardCrawlGame.languagePack.getPowerStrings(ID);
		name = strings.NAME;

		this.owner = owner;
		this.source = source;
		this.amount = amount;

		type = PowerType.DEBUFF;
		isTurnBased = false;

		// We load those txtures here.
		this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
		this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

		// priority = 0;

		updateDescription();
	}

	@Override
	public AbstractPower makeCopy()
	{
		return new BurningPower(owner, source, amount);
	}

	@Override
	public void updateDescription()
	{
		description = strings.DESCRIPTIONS[0] + amount + strings.DESCRIPTIONS[1];
	}

	@Override
	public void atEndOfRound()
	{
		if (this.amount <= 1)
		{
			this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, id));
		} else
		{
			this.addToBot(new ReducePowerAction(this.owner, this.owner, id, (int) Math.ceil(amount / 2d)));
		}
	}

	@Override
	public int onAttacked(DamageInfo info, int damageAmount)
	{
		if (info.type == DamageInfo.DamageType.NORMAL)
			addToTop(new DamageAction(owner, new DamageInfo(info.owner, amount, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.FIRE));
		return damageAmount;
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
