package boblovespihonkaimod.cards.skills;

import boblovespihonkaimod.DefaultMod;
import boblovespihonkaimod.cards.AbstractDynamicCard;
import boblovespihonkaimod.characters.TheDefault;
import boblovespihonkaimod.powers.CometWardPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class NekoCartwheel extends AbstractDynamicCard
{
	public static final String ID = DefaultMod.makeID("nekoCartwheel");
	public static final String IMG = DefaultMod.makeCardPath("Skill.png");

	// STAT DECLARATION
	public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;
	private static final CardRarity RARITY = CardRarity.COMMON;
	private static final CardTarget TARGET = CardTarget.SELF;
	private static final CardType TYPE = CardType.SKILL;
	private static final int COST = 1;
	private static final int BLOCK = 4;
	private static final int TIMES = 2;
	private static final int UPGRADE_TIMES = 1;

	public NekoCartwheel()
	{
		super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
		baseBlock = BLOCK;
		magicNumber = baseMagicNumber = TIMES;
	}

	//Upgraded stats.
	@Override
	public void upgrade()
	{
		if (!upgraded)
		{
			upgradeName();
			upgradeMagicNumber(UPGRADE_TIMES);
			initializeDescription();
		}
	}

	// Actions the card should do.
	@Override
	public void use(AbstractPlayer p, AbstractMonster m)
	{
		for (int i = 0; i < magicNumber; i++)
		{
			addToBot(new GainBlockAction(p, p, block));
		}
	}
}
