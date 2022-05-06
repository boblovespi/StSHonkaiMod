package boblovespihonkaimod.cards.attacks;

import boblovespihonkaimod.DefaultMod;
import boblovespihonkaimod.cards.AbstractDynamicCard;
import boblovespihonkaimod.characters.TheDefault;
import boblovespihonkaimod.powers.HonkaiPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Gunstorm extends AbstractDynamicCard
{
	public static final String ID = DefaultMod.makeID("gunstorm");
	public static final String IMG = DefaultMod.makeCardPath("Attack.png");

	// STAT DECLARATION
	public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;
	private static final CardRarity RARITY = CardRarity.UNCOMMON;
	private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
	private static final CardType TYPE = CardType.ATTACK;
	private static final int COST = 1;
	private static final int DAMAGE = 6;
	private static final int DAMAGE_UPGRADE = 0;
	private static final int MAGIC = 2;
	private static final int UPGRADE = 1;

	public Gunstorm()
	{
		super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
		baseDamage = DAMAGE;
		magicNumber = baseMagicNumber = MAGIC;
		isMultiDamage = true;
	}

	//Upgraded stats.
	@Override
	public void upgrade()
	{
		if (!upgraded)
		{
			upgradeName();
			upgradeDamage(DAMAGE_UPGRADE);
			upgradeMagicNumber(UPGRADE);
			initializeDescription();
		}
	}

	// Actions the card should do.
	@Override
	public void use(AbstractPlayer p, AbstractMonster m)
	{
		for (int i = 0; i < magicNumber; i++)
		{
			addToBot(new DamageAllEnemiesAction(p, damage, damageTypeForTurn, AbstractGameAction.AttackEffect.BLUNT_LIGHT));
		}
		addToBot(new ReducePowerAction(p, p, HonkaiPower.id, 5));
	}

	@Override
	public boolean canUse(AbstractPlayer p, AbstractMonster m)
	{
		int actual = 0;
		if (AbstractDungeon.player.hasPower(HonkaiPower.id))
			actual = AbstractDungeon.player.getPower(HonkaiPower.id).amount;
		return actual >= 5 && super.canUse(p, m);
	}
}
