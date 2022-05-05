package boblovespihonkaimod.cards.skills;

import boblovespihonkaimod.DefaultMod;
import boblovespihonkaimod.cards.AbstractDynamicCard;
import boblovespihonkaimod.characters.TheDefault;
import boblovespihonkaimod.powers.CometWardPower;
import boblovespihonkaimod.powers.HonkaiPower;
import com.evacipated.cardcrawl.mod.stslib.actions.tempHp.AddTemporaryHPAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class BySongDefended extends AbstractDynamicCard
{
	public static final String ID = DefaultMod.makeID("bySongDefended");
	public static final String IMG = DefaultMod.makeCardPath("Skill.png");

	// STAT DECLARATION
	public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;
	private static final CardRarity RARITY = CardRarity.RARE;
	private static final CardTarget TARGET = CardTarget.SELF;
	private static final CardType TYPE = CardType.SKILL;
	private static final int COST = 2;
	private static final int BLOCK = 16;
	private static final int UPGRADE_PLUS_BLOCK = 6;
	private static final int TEMPHP = 10;
	private static final int TEMPHP_UPGRADE = 4;
	private static final int TEMPHP_MULT = 2;

	public BySongDefended()
	{
		super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
		baseBlock = BLOCK;
		magicNumber = baseMagicNumber = 10;
	}

	//Upgraded stats.
	@Override
	public void upgrade()
	{
		if (!upgraded)
		{
			upgradeName();
			upgradeBlock(UPGRADE_PLUS_BLOCK);
			upgradeMagicNumber(TEMPHP_UPGRADE);
			initializeDescription();
		}
	}

	// Actions the card should do.
	@Override
	public void use(AbstractPlayer p, AbstractMonster m)
	{
		// addToBot(new DamageAction(p, new DamageInfo(p, 5, DamageInfo.DamageType.HP_LOSS)));
		addToBot(new GainBlockAction(p, p, block));
		addToBot(new AddTemporaryHPAction(p, p, magicNumber));
	}

	@Override
	public void applyPowers()
	{
		int add = 0;
		if (AbstractDungeon.player.hasPower(HonkaiPower.id))
			add = AbstractDungeon.player.getPower(HonkaiPower.id).amount * TEMPHP_MULT;
		magicNumber = add + baseMagicNumber;
		super.applyPowers();
		isMagicNumberModified = magicNumber != baseMagicNumber;
	}
}
