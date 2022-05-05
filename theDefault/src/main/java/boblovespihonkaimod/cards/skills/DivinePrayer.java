package boblovespihonkaimod.cards.skills;

import boblovespihonkaimod.DefaultMod;
import boblovespihonkaimod.cards.AbstractDynamicCard;
import boblovespihonkaimod.characters.TheDefault;
import boblovespihonkaimod.powers.HonkaiPower;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.HealEffect;

public class DivinePrayer extends AbstractDynamicCard
{
	public static final String ID = DefaultMod.makeID("divinePrayer");
	public static final String IMG = DefaultMod.makeCardPath("Skill.png");

	// STAT DECLARATION
	public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;
	private static final CardRarity RARITY = CardRarity.RARE;
	private static final CardTarget TARGET = CardTarget.SELF;
	private static final CardType TYPE = CardType.SKILL;
	private static final int COST = 0;
	private static final int MAGIC = 12;
	private static final int MAGIC_UPGRADE = 4;
	private static final int REMOVE = 6;

	public DivinePrayer()
	{
		super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
		magicNumber = baseMagicNumber = MAGIC;
		defaultSecondMagicNumber = defaultBaseSecondMagicNumber = REMOVE;
		exhaust = true;
		tags.add(CardTags.HEALING);
	}

	//Upgraded stats.
	@Override
	public void upgrade()
	{
		if (!upgraded)
		{
			upgradeName();
			upgradeMagicNumber(MAGIC_UPGRADE);
			upgradeDefaultSecondMagicNumber(MAGIC_UPGRADE);
			initializeDescription();
		}
	}

	// Actions the card should do.
	@Override
	public void use(AbstractPlayer p, AbstractMonster m)
	{
		addToBot(new VFXAction(new HealEffect(p.hb_x, p.hb_y, magicNumber)));
		addToBot(new HealAction(p, p, magicNumber));
		addToBot(new ReducePowerAction(p, p, HonkaiPower.id, defaultSecondMagicNumber));
	}

	@Override
	public void applyPowers()
	{
		int actual = 0;
		if (AbstractDungeon.player.hasPower(HonkaiPower.id))
			actual = AbstractDungeon.player.getPower(HonkaiPower.id).amount;
		magicNumber = Math.min(actual, baseMagicNumber);
		super.applyPowers();
		isMagicNumberModified = magicNumber != baseMagicNumber;
	}
}
