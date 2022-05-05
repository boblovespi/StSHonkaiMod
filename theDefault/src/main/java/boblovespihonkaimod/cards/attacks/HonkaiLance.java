package boblovespihonkaimod.cards.attacks;

import boblovespihonkaimod.DefaultMod;
import boblovespihonkaimod.cards.AbstractDynamicCard;
import boblovespihonkaimod.characters.TheDefault;
import boblovespihonkaimod.powers.HonkaiPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.SweepingBeamEffect;

public class HonkaiLance extends AbstractDynamicCard
{
	public static final String ID = DefaultMod.makeID("honkaiLance");
	public static final String IMG = DefaultMod.makeCardPath("Attack.png");

	// STAT DECLARATION
	public static final CardColor COLOR = CardColor.COLORLESS;
	private static final CardRarity RARITY = CardRarity.COMMON;
	private static final CardTarget TARGET = CardTarget.ENEMY;
	private static final CardType TYPE = CardType.ATTACK;
	private static final int COST = 0;
	private static final int DAMAGE = 8;
	private static final int DAMAGE_UPGRADE = 3;
	private static final int MAGIC = 3;
	private static final int UPGRADE = -1;

	public HonkaiLance()
	{
		super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
		baseDamage = DAMAGE;
		magicNumber = baseMagicNumber = MAGIC;
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
		addToBot(new VFXAction(new SweepingBeamEffect(m.hb_x, m.hb_y, false)));
		addToBot(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.NONE));
		addToBot(new ReducePowerAction(p, p, HonkaiPower.id, magicNumber));
	}

	@Override
	public boolean canUse(AbstractPlayer p, AbstractMonster m)
	{
		int actual = 0;
		if (AbstractDungeon.player.hasPower(HonkaiPower.id))
			actual = AbstractDungeon.player.getPower(HonkaiPower.id).amount;
		return actual >= magicNumber;
	}
}
