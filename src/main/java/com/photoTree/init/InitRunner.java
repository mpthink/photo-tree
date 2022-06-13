package com.photoTree.init;

import com.photoTree.service.FcSystemConfigService;
import com.photoTree.utils.NftDappEventUtils;
import com.photoTree.utils.SpringContextUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.photoTree.base.service.IBaseService;
import com.photoTree.constant.SysConfConstant;
import com.photoTree.service.StorageService;
import com.photoTree.storage.IpfsStorage;
import com.photoTree.storage.LocalStorage;

@Component
public class InitRunner implements CommandLineRunner {
	
	private static final Logger logger = LoggerFactory.getLogger(InitRunner.class);
	
	@Autowired
    FcSystemConfigService systemConfigService;
	
	@Autowired
	IBaseService baseService;
	
	@Override
	public void run(String... args) throws Exception {
		logger.info("开始初始化数据");
		this.initIpfs();
		this.initWeb3j();
		logger.info("初始化完成...");
	}
	
	private void initIpfs() {
		logger.info("初始化ipfs");
		String host = this.systemConfigService.get(SysConfConstant.IPFS_SERVER_IP);
		String port = this.systemConfigService.get(SysConfConstant.IPFS_SERVER_PORT);
		String remoteServer = this.systemConfigService.get(SysConfConstant.IPFS_REMOTE_SERVER);
		String localPath = this.systemConfigService.get(SysConfConstant.STORAGE_LOCAL_PATH);
		String requestBase = this.systemConfigService.get(SysConfConstant.STORAGE_REQUEST_BASE);
		StorageService storageService = SpringContextUtil.getBean(StorageService.class);
		IpfsStorage storage = new IpfsStorage();
		storage.setHost(host);
		storage.setPort(StringUtils.isEmpty(port)? 0 : Integer.valueOf(port));
		storage.setLoclLocation(StringUtils.isEmpty(localPath) ? "/" : localPath);
		storage.setRemoteService(remoteServer);
		storage.setRequestBase(requestBase);
		storageService.setIpfsStorage(storage);
		LocalStorage localStorage = new LocalStorage();
		localStorage.setAddress(requestBase);
		localStorage.setStoragePath(StringUtils.isEmpty(localPath) ? "/" : localPath);
		storageService.setStorage(localStorage);
		logger.info("初始化ipfs完成");
	}
	
	private void initWeb3j() {
		String url = this.systemConfigService.get(SysConfConstant.CHAIN_API_URL);
		logger.info("初始化web3j");
		NftDappEventUtils.initWeb3j(url);
		logger.info("初始化web3j完成");
	}
}
