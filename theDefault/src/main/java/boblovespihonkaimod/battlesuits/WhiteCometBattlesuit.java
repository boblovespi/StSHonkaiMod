package boblovespihonkaimod.battlesuits;

import basemod.abstracts.AbstractCardModifier;
import basemod.helpers.CardModifierManager;
import boblovespihonkaimod.DefaultMod;
import com.evacipated.cardcrawl.mod.stslib.actions.common.StunMonsterAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.StanceStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;

public class WhiteCometBattlesuit extends AbstractBattlesuit
{
	public static String ID = DefaultMod.makeID("whiteComet");
	private static final StanceStrings strings = CardCrawlGame.languagePack.getStanceString(ID);
	private int bonusDamage = 2;

	public WhiteCometBattlesuit()
	{
		super.ID = ID;
		name = strings.NAME;
		ultHonkaiCost = 10;
		ultEnergyCost = 3;
	}

	@Override
	public void onUltUse()
	{
		AbstractDungeon.actionManager.addToBottom(new TalkAction(true, strings.DESCRIPTION[1], 4.0f, 2.0f));
		AbstractDungeon.actionManager.addToBottom(new GainBlockAction(AbstractDungeon.player, 20));
		for (AbstractMonster mon : AbstractDungeon.getMonsters().monsters)
		{
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mon, AbstractDungeon.player, new VulnerablePower(mon, 1, false)));
		}
		AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(AbstractDungeon.player, 20, DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.SLASH_HEAVY));
	}

	@Override
	public void updateDescription()
	{
		description = strings.DESCRIPTION[0];
	}

	@Override
	public void onPlayCard(AbstractCard card)
	{
		super.onPlayCard(card);
	}

	@Override
	public void onEnterStance()
	{

	}

	@Override
	public void onExitOrVictory()
	{
		AbstractDungeon.player.masterDeck.group.forEach(this::removeBlockMod);
		AbstractDungeon.player.hand.group.forEach(this::removeBlockMod);
		AbstractDungeon.player.drawPile.group.forEach(this::removeBlockMod);
		AbstractDungeon.player.discardPile.group.forEach(this::removeBlockMod);
		AbstractDungeon.player.limbo.group.forEach(this::removeBlockMod);
		AbstractDungeon.player.exhaustPile.group.forEach(this::removeBlockMod);
	}

	@Override
	public void update()
	{
		super.update();
		if (AbstractDungeon.player.hoveredCard != null)
			addBlockMod(AbstractDungeon.player.hoveredCard);
		AbstractDungeon.player.hand.group.forEach(this::addBlockMod);
	}

	@Override
	public float atDamageGive(float damage, DamageInfo.DamageType type, AbstractCard card)
	{
		if (type == DamageInfo.DamageType.NORMAL && card.type == AbstractCard.CardType.ATTACK)
			if (card.rarity == AbstractCard.CardRarity.BASIC || card.rarity == AbstractCard.CardRarity.COMMON)
				return damage + bonusDamage;
		return damage;
	}

	private void addBlockMod(AbstractCard card)
	{
		if (card.type == AbstractCard.CardType.SKILL && !CardModifierManager.hasModifier(card, BlockMod.ID))
			if (card.rarity == AbstractCard.CardRarity.COMMON || card.rarity == AbstractCard.CardRarity.BASIC)
				CardModifierManager.addModifier(card, new BlockMod());
	}

	private void removeBlockMod(AbstractCard card)
	{
		CardModifierManager.removeModifiersById(card, BlockMod.ID, false);
	}

	public static class BlockMod extends AbstractCardModifier
	{
		public static String ID = DefaultMod.makeID("blockMod");

		@Override
		public float modifyBlock(float block, AbstractCard card)
		{
			return block + 2;
		}

		@Override
		public AbstractCardModifier makeCopy()
		{
			return new BlockMod();
		}

		@Override
		public String identifier(AbstractCard card)
		{
			return ID;
		}
	}
}
