package boblovespihonkaimod.powers;

import basemod.interfaces.CloneablePowerInterface;
import boblovespihonkaimod.DefaultMod;
import boblovespihonkaimod.util.TextureLoader;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class StarFlamePower extends AbstractPower implements CloneablePowerInterface
{
	private static final Texture tex84 = TextureLoader.getTexture(DefaultMod.makePowerPath("placeholder_power84.png"));
	private static final Texture tex32 = TextureLoader.getTexture(DefaultMod.makePowerPath("placeholder_power32.png"));
	public static String id = DefaultMod.makeID("starFlamePower");
	public PowerStrings strings;
	private AbstractCreature source;
	private boolean usedThisTurn = false;

	public StarFlamePower(final AbstractCreature owner, final AbstractCreature source, final int amount)
	{
		ID = id;
		strings = CardCrawlGame.languagePack.getPowerStrings(ID);
		name = strings.NAME;

		this.owner = owner;
		this.source = source;
		this.amount = amount;

		type = PowerType.BUFF;

		// We load those txtures here.
		this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
		this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

		updateDescription();
	}

	@Override
	public AbstractPower makeCopy()
	{
		return new StarFlamePower(owner, source, amount);
	}

	@Override
	public void updateDescription()
	{
		description = strings.DESCRIPTIONS[0] + amount + strings.DESCRIPTIONS[1];
	}

	@Override
	public void atStartOfTurn()
	{
		usedThisTurn = false;
	}

	@Override
	public void onUseCard(AbstractCard card, UseCardAction action)
	{
		if (card.type == AbstractCard.CardType.ATTACK && !usedThisTurn)
		{
			flash();
			addToBot(new ApplyPowerAction(action.target, action.source, new BurningPower(action.target, owner, amount)));
			usedThisTurn = true;
		}
	}
}