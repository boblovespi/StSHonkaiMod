package boblovespihonkaimod.cards.skills;

import boblovespihonkaimod.DefaultMod;
import boblovespihonkaimod.cards.AbstractDynamicCard;
import boblovespihonkaimod.characters.TheDefault;
import boblovespihonkaimod.powers.HonkaiPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class OverdriveCore extends AbstractDynamicCard
{
	public static final String ID = DefaultMod.makeID("overdriveCore");
	public static final String IMG = DefaultMod.makeCardPath("Skill.png");

	// STAT DECLARATION
	public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;
	private static final CardRarity RARITY = CardRarity.UNCOMMON;
	private static final CardTarget TARGET = CardTarget.SELF;
	private static final CardType TYPE = CardType.SKILL;
	private static final int COST = 1;
	private static final int ENERGY = 2;
	private static final int UPGRADE_PLUS_BLOCK = 4;
	private static final int MAGIC = 3;
	private static final int MAGIC_UPGRADE = 2;

	public OverdriveCore()
	{
		super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
		magicNumber = baseMagicNumber = ENERGY;
		defaultSecondMagicNumber = defaultBaseSecondMagicNumber = MAGIC;
	}

	//Upgraded stats.
	@Override
	public void upgrade()
	{
		if (!upgraded)
		{
			upgradeName();
			upgradeDefaultSecondMagicNumber(MAGIC_UPGRADE);
			upgradeBaseCost(0);
			initializeDescription();
		}
	}

	// Actions the card should do.
	@Override
	public void use(AbstractPlayer p, AbstractMonster m)
	{
		addToBot(new GainEnergyAction(magicNumber));
		addToBot(new ApplyPowerAction(p, p, new HonkaiPower(p, p, defaultSecondMagicNumber)));
	}
}
