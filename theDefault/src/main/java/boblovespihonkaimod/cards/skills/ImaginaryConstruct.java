package boblovespihonkaimod.cards.skills;

import boblovespihonkaimod.DefaultMod;
import boblovespihonkaimod.cards.AbstractDynamicCard;
import boblovespihonkaimod.characters.TheDefault;
import boblovespihonkaimod.powers.HonkaiPower;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.status.VoidCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class ImaginaryConstruct extends AbstractDynamicCard
{
	public static final String ID = DefaultMod.makeID("imaginaryConstruct");
	public static final String IMG = DefaultMod.makeCardPath("Skill.png");

	// STAT DECLARATION
	public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;
	private static final CardRarity RARITY = CardRarity.UNCOMMON;
	private static final CardTarget TARGET = CardTarget.SELF;
	private static final CardType TYPE = CardType.SKILL;
	private static final int COST = 0;
	private static final int DRAW = 1;
	private static final int UPGRADE_DRAW = 0;
	private static final int ENERGY = 10;
	private static final int ENERGY_UPGRADE = -3;
	private static final int VOIDS = 2;

	public ImaginaryConstruct()
	{
		super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
		// baseDraw = draw = DRAW;
		magicNumber = baseMagicNumber = ENERGY;
		// defaultBaseSecondMagicNumber = defaultSecondMagicNumber = VOIDS;
		exhaust = true;
	}

	//Upgraded stats.
	@Override
	public void upgrade()
	{
		if (!upgraded)
		{
			upgradeName();
			// baseDraw += UPGRADE_DRAW;
			// draw = baseDraw;
			upgradeMagicNumber(ENERGY_UPGRADE);
			initializeDescription();
		}
	}

	// Actions the card should do.
	@Override
	public void use(AbstractPlayer p, AbstractMonster m)
	{
		CardGroup raresButHealing = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
		for (AbstractCard rare : AbstractDungeon.rareCardPool.group)
		{
			if (!rare.hasTag(CardTags.HEALING))
				raresButHealing.addToTop(rare);
		}
		AbstractCard card = raresButHealing.getRandomCard(false);
		if (upgraded)
			card.upgrade();
		addToBot(new MakeTempCardInHandAction(card));
		addToBot(new MakeTempCardInDrawPileAction(new VoidCard(), 1, true, true));
		addToBot(new ReducePowerAction(p, p, HonkaiPower.id, magicNumber));
	}

	@Override
	public boolean canUse(AbstractPlayer p, AbstractMonster m)
	{
		int actual = 0;
		if (AbstractDungeon.player.hasPower(HonkaiPower.id))
			actual = AbstractDungeon.player.getPower(HonkaiPower.id).amount;
		return actual >= magicNumber && super.canUse(p, m);
	}
}
