package boblovespihonkaimod.cards;

import boblovespihonkaimod.DefaultMod;
import boblovespihonkaimod.powers.HonkaiPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class HonkaiEnergyStatus extends AbstractDynamicCard
{
	public static final String ID = DefaultMod.makeID("honkaiEnergy");
	public static final String IMG = DefaultMod.makeCardPath("Skill.png");

	// STAT DECLARATION
	public static final CardColor COLOR = CardColor.COLORLESS;
	private static final CardRarity RARITY = CardRarity.COMMON;
	private static final CardTarget TARGET = CardTarget.NONE;
	private static final CardType TYPE = CardType.STATUS;
	private static final int COST = 1;
	private static final int DAMAGE = 7;
	private static final int DAMAGE_UPGRADE = 2;
	private static final int MAGIC = 1;
	private static final int UPGRADE = 1;

	public HonkaiEnergyStatus()
	{
		super(ID, IMG, -2, TYPE, COLOR, RARITY, TARGET);
		isEthereal = true;
		baseMagicNumber = magicNumber = MAGIC;
	}

	//Upgraded stats.
	@Override
	public void upgrade()
	{
		if (!upgraded)
		{
			upgradeName();
			upgradeMagicNumber(UPGRADE);
			initializeDescription();
		}
	}

	@Override
	public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster)
	{

	}

	@Override
	public void triggerWhenDrawn()
	{
		addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new HonkaiPower(AbstractDungeon.player, AbstractDungeon.player, magicNumber)));
	}
}
