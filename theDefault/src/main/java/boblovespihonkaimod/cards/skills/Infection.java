package boblovespihonkaimod.cards.skills;

import boblovespihonkaimod.DefaultMod;
import boblovespihonkaimod.cards.AbstractDynamicCard;
import boblovespihonkaimod.characters.TheDefault;
import boblovespihonkaimod.powers.HonkaiPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.cards.status.VoidCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Infection extends AbstractDynamicCard
{
	public static final String ID = DefaultMod.makeID("infection");
	public static final String IMG = DefaultMod.makeCardPath("Skill.png");

	// STAT DECLARATION
	public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;
	private static final CardRarity RARITY = CardRarity.COMMON;
	private static final CardTarget TARGET = CardTarget.SELF;
	private static final CardType TYPE = CardType.SKILL;
	private static final int COST = 0;
	private static final int DRAW = 1;
	private static final int UPGRADE_DRAW = 0;
	private static final int ENERGY = 4;
	private static final int ENERGY_UPGRADE = 2;
	private static final int VOIDS = 2;

	public Infection()
	{
		super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
		baseDraw = draw = DRAW;
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
		addToBot(new DrawCardAction(draw));
		addToBot(new ApplyPowerAction(p, p, new HonkaiPower(p, p, magicNumber)));
	}
}
