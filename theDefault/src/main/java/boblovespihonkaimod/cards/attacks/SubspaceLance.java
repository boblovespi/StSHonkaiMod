package boblovespihonkaimod.cards.attacks;

import boblovespihonkaimod.DefaultMod;
import boblovespihonkaimod.cards.AbstractDynamicCard;
import boblovespihonkaimod.characters.TheDefault;
import boblovespihonkaimod.powers.HonkaiPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.status.VoidCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.CollectorCurseEffect;

public class SubspaceLance extends AbstractDynamicCard
{
	public static final String ID = DefaultMod.makeID("subspaceLance");
	public static final String IMG = DefaultMod.makeCardPath("Attack.png");

	// STAT DECLARATION
	public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;
	private static final CardRarity RARITY = CardRarity.RARE;
	private static final CardTarget TARGET = CardTarget.ENEMY;
	private static final CardType TYPE = CardType.ATTACK;
	private static final int COST = 1;
	private static final int DAMAGE = 4;
	private static final int DAMAGE_UPGRADE = 1;
	private static final int TIMES = 4;
	private static final int UPGRADE = 1;

	public SubspaceLance()
	{
		super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
		baseDamage = DAMAGE;
		defaultSecondMagicNumber = defaultBaseSecondMagicNumber = TIMES;
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
			upgradeDefaultSecondMagicNumber(UPGRADE);
			initializeDescription();
		}
	}

	// Actions the card should do.
	@Override
	public void use(AbstractPlayer p, AbstractMonster m)
	{
		addToBot(new SFXAction("MONSTER_COLLECTOR_DEBUFF"));
		addToBot(new VFXAction(new CollectorCurseEffect(m.hb.cX, m.hb.cY), 2.0F));
		for (int i = 0; i < defaultSecondMagicNumber; i++)
		{
			addToBot(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.LIGHTNING));
		}
		addToBot(new MakeTempCardInDrawPileAction(new VoidCard(), 1, true, true));
	}

	public void applyPowers()
	{
		int realBaseDamage = baseDamage;
		if (AbstractDungeon.player.hasPower(HonkaiPower.id))
			baseMagicNumber = AbstractDungeon.player.getPower(HonkaiPower.id).amount / 2;
		else
			baseMagicNumber = 0;
		baseDamage += baseMagicNumber;
		super.applyPowers();
		baseDamage = realBaseDamage;
		isDamageModified = damage != baseDamage;
	}

	public void calculateCardDamage(AbstractMonster mo)
	{
		int realBaseDamage = baseDamage;
		if (AbstractDungeon.player.hasPower(HonkaiPower.id))
			baseMagicNumber = AbstractDungeon.player.getPower(HonkaiPower.id).amount / 2;
		else
			baseMagicNumber = 0;
		baseDamage += baseMagicNumber;
		super.calculateCardDamage(mo);
		baseDamage = realBaseDamage;
		isDamageModified = damage != baseDamage;
	}
}
