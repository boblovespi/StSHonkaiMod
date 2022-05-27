package boblovespihonkaimod.battlesuits;

import basemod.abstracts.AbstractCardModifier;
import basemod.helpers.CardModifierManager;
import boblovespihonkaimod.DefaultMod;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.StanceStrings;

public class HOVBattlesuit extends AbstractBattlesuit
{
	public static String ID = DefaultMod.makeID("hov");
	private static final StanceStrings strings = CardCrawlGame.languagePack.getStanceString(ID);

	public HOVBattlesuit()
	{
		super.ID = ID;
		name = strings.NAME;
	}

	@Override
	public void onUltUse()
	{
		// todo: figure out
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
				return damage + 2;
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
