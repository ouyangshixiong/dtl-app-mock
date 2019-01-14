/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { DtlAppMockTestModule } from '../../../test.module';
import { BankcardInfoUpdateComponent } from 'app/entities/bankcard-info/bankcard-info-update.component';
import { BankcardInfoService } from 'app/entities/bankcard-info/bankcard-info.service';
import { BankcardInfo } from 'app/shared/model/bankcard-info.model';

describe('Component Tests', () => {
    describe('BankcardInfo Management Update Component', () => {
        let comp: BankcardInfoUpdateComponent;
        let fixture: ComponentFixture<BankcardInfoUpdateComponent>;
        let service: BankcardInfoService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [DtlAppMockTestModule],
                declarations: [BankcardInfoUpdateComponent]
            })
                .overrideTemplate(BankcardInfoUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(BankcardInfoUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(BankcardInfoService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new BankcardInfo(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.bankcardInfo = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.update).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );

            it(
                'Should call create service on save for new entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new BankcardInfo();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.bankcardInfo = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.create).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );
        });
    });
});
