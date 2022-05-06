package boblovespihonkaimod.cards.skills;

import boblovespihonkaimod.DefaultMod;
import boblovespihonkaimod.cards.AbstractDynamicCard;
import boblovespihonkaimod.characters.TheDefault;
import boblovespihonkaimod.powers.HonkaiPower;
import com.evacipated.cardcrawl.mod.stslib.actions.common.StunMonsterAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Zeitstrom extends AbstractDynamicCard
{
	public static final String ID = DefaultMod.makeID("zeitstrom");
	public static final String IMG = DefaultMod.makeCardPath("Skill.png");

	// STAT DECLARATION
	public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;
	private static final CardRarity RARITY = CardRarity.RARE;
	private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
	private static final CardType TYPE = CardType.SKILL;
	private static final int COST = 2;
	private static final int UPGRADED_COST = 1;

	public Zeitstrom()
	{
		super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
		exhaust = true;
	}

	//Upgraded stats.
	@Override
	public void upgrade()
	{
		if (!upgraded)
		{
			upgradeName();
			initializeDescription();
			upgradeBaseCost(UPGRADED_COST);
		}
	}

	// Actions the card should do.
	@Override
	public void use(AbstractPlayer p, AbstractMonster m)
	{
		// addToBot(new DamageAction(p, new DamageInfo(p, 5, DamageInfo.DamageType.HP_LOSS)));
		for (AbstractMonster mon : AbstractDungeon.getMonsters().monsters)
		{
			addToBot(new StunMonsterAction(mon, p));
		}
	}
}
