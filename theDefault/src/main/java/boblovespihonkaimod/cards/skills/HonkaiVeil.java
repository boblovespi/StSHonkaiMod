package boblovespihonkaimod.cards.skills;

import boblovespihonkaimod.DefaultMod;
import boblovespihonkaimod.cards.AbstractDynamicCard;
import boblovespihonkaimod.characters.TheDefault;
import boblovespihonkaimod.powers.HonkaiPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class HonkaiVeil extends AbstractDynamicCard
{
	public static final String ID = DefaultMod.makeID("honkaiVeil");
	public static final String IMG = DefaultMod.makeCardPath("Skill.png");

	// STAT DECLARATION
	public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;
	private static final CardRarity RARITY = CardRarity.UNCOMMON;
	private static final CardTarget TARGET = CardTarget.SELF;
	private static final CardType TYPE = CardType.SKILL;
	private static final int COST = 1;
	private static final int BLOCK = 0;
	private static final int UPGRADE_PLUS_BLOCK = 5;
	private static final int MAGIC = 0;
	private static final int MAGIC_UPGRADE = 5;

	public HonkaiVeil()
	{
		super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
		baseBlock = BLOCK;
		// magicNumber = baseMagicNumber = MAGIC;
	}

	//Upgraded stats.
	@Override
	public void upgrade()
	{
		if (!upgraded)
		{
			upgradeName();
			upgradeBlock(UPGRADE_PLUS_BLOCK);
			// upgradeMagicNumber(MAGIC_UPGRADE);
			initializeDescription();
		}
	}

	// Actions the card should do.
	@Override
	public void use(AbstractPlayer p, AbstractMonster m)
	{
		int extra = 0;
		if (p.hasPower(HonkaiPower.id))
			extra = p.getPower(HonkaiPower.id).amount;
		addToBot(new GainBlockAction(p, p, block + extra));
	}
}
