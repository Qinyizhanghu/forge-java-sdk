package io.arcblock.forge;

/**
 * This is Itx type urls for protobuf Any
 */
public class TypeUrls {
  public static final String ACCOUNT_MIGRATE = "fg:t:account_migrate";
  public static final String CREATE_ASSET = "fg:t:create_asset";
  public static final String CONSUME_ASSET = "fg:t:consume_asset";
  public static final String POKE = "fg:t:poke";

  public static final String CONSENSUE_UPGRADE = "fg:t:consensus_upgrade";
  public static final String DECLARE = "fg:t:declare";
  public static final String DECLARE_FILE = "fg:t:declare_file";
  public static final String EXCHANGE = "fg:t:exchange";
  public static final String STAKE = "fg:t:stake";
  public static final String SYS_UPGRADE = "fg:t:sys_upgrade";
  public static final String TRANSFER = "fg:t:transfer";
  public static final String UPDATE_ASSET = "fg:t:update_asset";
  public static final String ACQUIRE_ASSET = "fg:t:acquire_asset";
  public static final String DEPOSIT_TETHER = "fg:t:deposit_tether";
  public static final String EXCHANGE_TETHER = "fg:t:exchange_tether";
  public static final String DELEGATE = "fg:t:delegate";
  public static final String REVOKE_DELEGATE = "fg:t:revoke_delegate";

  public static final String APPROVE_WITHDRAW= "fg:t:approve_withdraw";
  public static final String DEPOSIT_TOKEN= "fg:t:deposit_token";
  public static final String REVOKE_WITHDRAW= "fg:t:revoke_withdraw";
  public static final String WITHDRAW_TOKEN= "fg:t:withdraw_token";

  //atomic swap
  public static final String SETUP_SWAP= "fg:t:setup_swap";
  public static final String RETRIEVE_SWAP= "fg:t:retrieve_swap";
  public static final String REVOKE_SWAP=  "fg:t:revoke_swap";

  // forge state
  public static final String ACCOUNT_STATE = "fg:s:account";
  public static final String ASSET_STATE = "fg:s:asset";
  public static final String FORGE_STATE = "fg:s:forge";
  public static final String STAKE_STATE = "fg:s:stake";
  public static final String STATISTICS_STATE = "fg:s:statistics";

  // forge tx stake
  public static final String STAKE_FOR_NODE = "fg:x:stake_node";
  public static final String STAKE_FOR_USER = "fg:x:stake_user";
  public static final String STAKE_FOR_ASSET = "fg:x:stake_asset";
  public static final String STAKE_FOR_CHAIN = "fg:x:stake_chain";
  public static final String CONSUME_ASSET_ADDRESS = "fg:x:address";

  //
  public static final String TRANSACTION = "fg:x:tx";
  public static final String TRANSACTION_INFO = "fg:x:tx_info";
  public static final String TX_STATUS = "fg:x:tx_status";
  public static final String ADDRESS = "fg:x:account_migrate";
}
